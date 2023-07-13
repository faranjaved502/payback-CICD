package com.hamza.payback.data.services

import com.hamza.payback.core.domain.ApiResult
import com.hamza.payback.data.feed.domain.FeedData

interface FeedService {
    suspend fun getFeeds(
        key: String,
        query: String,
        imageType: String,
        pretty: Boolean
    ): ApiResult<FeedData>
}