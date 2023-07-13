package com.hamza.payback.api.feeds.mapper

import com.hamza.payback.data.feed.network.mapper.FeedsMapper
import com.hamza.payback.data.feed.network.response.FeedsDTO
import com.hamza.payback.data.feed.network.response.FeedsResultResponseDTO
import com.hamza.payback.framework.AppBaseTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FeedsMapperTest: AppBaseTest() {

    private lateinit var feedsMapper: FeedsMapper

    @Before
    fun setUp() {
        super.setUpBase()
        feedsMapper = FeedsMapper()
    }

    @Test
    fun testFeedsMapperSuccess() {
        //given
        val feedsList = listOf(
            FeedsDTO(
                id="id-1",
                pageURL ="pageURL-1",
                type= "type-1",
                tags ="tags-1",
                previewURL = "previewURL-1",
                webFormatURL= "webFormatURL-1",
                largeImageURL= "largeImageURL-1",
                user = "user-1",
                userImageURL = "userImageURL-1",
                previewWidth = 1,
                previewHeight = 1,
                webFormatWidth = 1,
                webFormatHeight = 1,
                views = 1,
                downloads = 1,
                collections = 1,
                likes = 1,
                comments = 1,
                userId = 1,
            ),
            FeedsDTO(
                id="id-2",
                pageURL ="pageURL-2",
                type= "type-2",
                tags ="tags-2",
                previewURL = "previewURL-2",
                webFormatURL= "webFormatURL-2",
                largeImageURL= "largeImageURL-2",
                user = "user-2",
                userImageURL = "userImageURL-2",
                previewWidth = 2,
                previewHeight = 2,
                webFormatWidth = 2,
                webFormatHeight = 2,
                views = 2,
                downloads = 2,
                collections = 2,
                likes = 2,
                comments = 2,
                userId = 2,
            ),
            FeedsDTO(
                id="id-3",
                pageURL ="pageURL-3",
                type= "type-3",
                tags ="tags-3",
                previewURL = "previewURL-3",
                webFormatURL= "webFormatURL-3",
                largeImageURL= "largeImageURL-3",
                user = "user-3",
                userImageURL = "userImageURL-3",
                previewWidth = 3,
                previewHeight = 3,
                webFormatWidth = 3,
                webFormatHeight = 3,
                views = 3,
                downloads = 3,
                collections = 3,
                likes = 3,
                comments = 3,
                userId = 3,
            )
        )

        val feedResponse = FeedsResultResponseDTO(
            total = 2,
            totalHits = 2,
            hits = feedsList
        )

        //when
        val result = feedsMapper.map(feedResponse)

        //assert
        assertTrue(result.hits?.isNotEmpty() == true)
        assertEquals(result.hits?.size, 3)
        assertEquals(result.hits?.get(0)?.id, "id-1")
        assertEquals(result.hits?.get(1)?.user, "user-2")
        assertEquals(result.hits?.get(2)?.previewURL, "previewURL-3")
    }
}