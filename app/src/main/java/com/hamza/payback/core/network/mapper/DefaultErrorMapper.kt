package com.hamza.payback.core.network.mapper


import com.hamza.payback.core.domain.ErrorType
import com.hamza.payback.core.domain.Error
import com.hamza.payback.core.network.response.ErrorResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody

class DefaultErrorMapper : ErrorMapper {

    override fun map(response: ResponseBody): Error =
        try {
            val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)

            val errorResponse = adapter.fromJson(response.string())
            errorResponse?.error?.let {
                Error(
                    it.code ?:  "",
                    it.message ?: "",
                    ErrorType.HTTP
                )
            } ?: Error("", "", ErrorType.GENERIC)
        } catch (e: JsonEncodingException) {
            Error("", e.message ?: "", ErrorType.CONVERSION)
        } catch (e: Exception) {
            throw e
        }

    override fun getApiResponseType(): Class<ResponseBody> {
        return ResponseBody::class.java
    }
}