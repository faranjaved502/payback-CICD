package com.hamza.payback.core.utils

import com.hamza.payback.core.domain.ApiResult


fun ApiResult<*>.isError(): Boolean {
    return error != null
}

fun ApiResult<*>.isSuccess(): Boolean {
    return !isError()
}