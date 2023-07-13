package com.hamza.payback.core.network.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorResponse(
    @Json(name = "error")
    var error: Error? = null,
) : Parcelable

@Parcelize
data class Error(
    @Json(name = "code")
    var code: String? = null,
    @Json(name = "message")
    var message: String? = null
) : Parcelable