package com.hamza.payback.data.feed.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedsDTO(
    @Json(name = "id") val id: String?,
    @Json(name = "pageURL") val pageURL: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "tags") val tags: String?,
    @Json(name = "previewURL") val previewURL: String?,
    @Json(name = "webformatURL") val webFormatURL: String?,
    @Json(name = "largeImageURL") val largeImageURL: String?,
    @Json(name = "user") val user: String?,
    @Json(name = "userImageURL") val userImageURL: String?,
    @Json(name = "previewWidth") val previewWidth: Int?,
    @Json(name = "previewHeight") val previewHeight: Int?,
    @Json(name = "webformatWidth") val webFormatWidth: Int?,
    @Json(name = "webformatHeight") val webFormatHeight: Int?,
    @Json(name = "views") val views: Int?,
    @Json(name = "downloads") val downloads: Int?,
    @Json(name = "collections") val collections: Int?,
    @Json(name = "likes") val likes: Int?,
    @Json(name = "comments") val comments: Int?,
    @Json(name = "user_id") val userId: Int?
)
