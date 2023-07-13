package com.hamza.payback.data.feed.network.mapper

import com.hamza.payback.core.network.mapper.DomainMapper
import com.hamza.payback.data.feed.domain.FeedData
import com.hamza.payback.data.feed.domain.Feeds
import com.hamza.payback.data.feed.network.response.FeedsResultResponseDTO

class FeedsMapper : DomainMapper<FeedsResultResponseDTO, FeedData> {

    override fun map(response: FeedsResultResponseDTO): FeedData  =
        FeedData(response.hits?.map { Feeds(id = it.id, user = it.user, previewURL = it.previewURL, userImageURL = it.userImageURL) })

    override fun getApiResponseType(): Class<FeedsResultResponseDTO> =
        FeedsResultResponseDTO::class.java
}