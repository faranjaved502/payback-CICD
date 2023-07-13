package com.hamza.payback.data.feed.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedsResultResponseDTO(
    @Json(name = "total") val total: Int?,
    @Json(name = "totalHits") val totalHits: Int?,
    @Json(name = "hits") val hits: List<FeedsDTO>?
)