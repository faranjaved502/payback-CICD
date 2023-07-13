package com.hamza.payback.core.network

import com.hamza.payback.core.domain.ApiResult
import com.hamza.payback.core.domain.ErrorType
import com.hamza.payback.core.network.mapper.DefaultErrorMapper
import com.hamza.payback.core.network.mapper.DomainMapper
import com.hamza.payback.core.network.mapper.ErrorMapper
import com.hamza.payback.core.domain.Error
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.*
import javax.net.ssl.SSLException

open class RetrofitCallExecutor<NetworkT, DomainT> constructor(
    private val domainMapper: DomainMapper<NetworkT, DomainT>? = null,
    private val errorMapper: ErrorMapper = DefaultErrorMapper()
) {
    fun execute(call: Call<NetworkT>): ApiResult<DomainT> {

        //Create a base object to return in case any error or conversion failed
        val apiResult: ApiResult<DomainT>

        try {
            val response = call.execute()
            apiResult = if (response.isSuccessful) {
                returnResponse(response)
            } else {
                returnErrorResponse(response)
            }
        } catch (e: IOException) {
            return (handleNetworkError(e))
        }
        return apiResult
    }

    private fun returnResponse(response: Response<NetworkT>): ApiResult<DomainT> {
        val serverDate = getHeaderDate(response)
        var domain: DomainT? = null
        if (domainMapper != null) {
            try {
                domain = response.body()?.let { domainMapper.map(it) }
            } catch (e: Exception) {
                return ApiResult(
                    error = Error(
                        "",
                        e.message ?: "",
                        ErrorType.CONVERSION
                    )
                )
            }
        }

        return ApiResult(
            response = domain,
            serverDate = serverDate,
            httpStatusCode = response.code(),
            headers = response.headers()
        )
    }

    private fun returnErrorResponse(response: Response<NetworkT>): ApiResult<DomainT> {
        var errorDomain: Error? = null
        try {
            errorDomain = response.errorBody()?.let { errorMapper.map(it) }
        } catch (e: Exception) {
            return (handleNetworkError(e))
        }
        return ApiResult(error = errorDomain, httpStatusCode = response.code())
    }

    private fun handleNetworkError(
        throwable: Throwable
    ): ApiResult<DomainT> {
        return ApiResult(error = mapThrowableToError(throwable))
    }

    private fun mapThrowableToError(throwable: Throwable): Error {
        val errorType = when (throwable) {
            is SSLException -> ErrorType.SSL
            is IOException -> ErrorType.NETWORK
            else -> ErrorType.GENERIC
        }

        return Error(
            errorCode = "",
            errorMessage = throwable.message ?: "",
            errorType = errorType
        )
    }

    protected fun <NetworkT> getHeaderDate(response: Response<NetworkT>?): Date? {
        //Will be good to have some sort of server data. So in this case
        return Date()
    }
}
