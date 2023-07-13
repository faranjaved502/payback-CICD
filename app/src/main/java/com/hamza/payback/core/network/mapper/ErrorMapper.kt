package com.hamza.payback.core.network.mapper

import com.hamza.payback.core.domain.Error
import okhttp3.ResponseBody

interface ErrorMapper {

    fun map(response: ResponseBody) : Error

    fun getApiResponseType() : Class<ResponseBody>
}