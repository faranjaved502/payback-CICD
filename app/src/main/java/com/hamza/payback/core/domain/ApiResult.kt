package com.hamza.payback.core.domain

import okhttp3.Headers
import java.util.Date

data class ApiResult<DomainT>(
    val httpStatusCode: Int = -1,
    val serverDate: Date? = null,
    val response: DomainT? = null,
    val error: Error? = null,
    val headers: Headers? = null
)
