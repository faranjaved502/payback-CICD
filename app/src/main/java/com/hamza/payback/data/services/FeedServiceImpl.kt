package com.hamza.payback.data.services

import com.hamza.payback.core.domain.ApiResult
import com.hamza.payback.core.network.RetrofitCallExecutor
import com.hamza.payback.data.api.ApiService
import com.hamza.payback.data.feed.domain.FeedData
import com.hamza.payback.data.feed.network.mapper.FeedsMapper
import javax.inject.Inject

class FeedServiceImpl @Inject constructor(
    private val apiService: ApiService
): FeedService {

    override suspend fun getFeeds(
        key: String,
        query: String,
        imageType: String,
        pretty: Boolean
    ): ApiResult<FeedData> {
        val call = apiService.getFeedsList(key,query,imageType,pretty)

        return RetrofitCallExecutor(domainMapper = FeedsMapper()).execute(call)
    }
}