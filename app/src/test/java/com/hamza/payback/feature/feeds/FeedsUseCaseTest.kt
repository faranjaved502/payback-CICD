package com.hamza.payback.feature.feeds

import com.hamza.payback.core.domain.ApiResult
import com.hamza.payback.core.domain.Error
import com.hamza.payback.core.domain.ErrorType
import com.hamza.payback.data.feed.domain.FeedData
import com.hamza.payback.data.feed.domain.Feeds
import com.hamza.payback.data.services.FeedService
import com.hamza.payback.domain.usecases.feeds.FeedsUseCase
import com.hamza.payback.domain.usecases.feeds.FeedsUseCaseImpl
import com.hamza.payback.framework.AppBaseTest
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FeedsUseCaseTest: AppBaseTest() {

    lateinit var useCase: FeedsUseCase

    @Mock
    lateinit var feedServiceMock: FeedService

    @Before
    fun execute() {
        super.setUpBase()
        useCase = FeedsUseCaseImpl(feedServiceMock)
    }

    @Test
    fun testGetFeedsSuccess() = runBlocking {
        //given
        val key = "mock_key"
        val query = "mock_query"
        val imageType = "mock_image_type"

        //when
        whenever(feedServiceMock.getFeeds(key, query, imageType, true))
            .thenReturn(getMockFeedResultSuccess())

        //then
        val result = useCase.execute(FeedsUseCase.Params(key, query, imageType, true))

        //assert
        assertFalse(result is FeedsUseCase.Result.Error)
        assertTrue(result is FeedsUseCase.Result.Success)
    }

    @Test
    fun testGetFeedsError() = runBlocking {
        //given
        val key = "mock_key"
        val query = "mock_query"
        val imageType = "mock_image_type"

        //when
        whenever(feedServiceMock.getFeeds(key, query, imageType, true))
            .thenReturn(ApiResult(error = Error("code", "message", ErrorType.GENERIC)))

        //then
        val result = useCase.execute(FeedsUseCase.Params(key, query, imageType, true))

        //assert
        assertFalse(result is FeedsUseCase.Result.Success)
        assertTrue(result is FeedsUseCase.Result.Error)
    }


    private fun getMockFeedResultSuccess(): ApiResult<FeedData> {
        return ApiResult(
            httpStatusCode = 200,
            serverDate = null,
            response = getMockFeedResponse(),
            error = null
        )
    }

    private fun getMockFeedResponse() = FeedData(
        listOf(
            Feeds(
                id = "1",
                user = "abc",
                previewURL = "previewURL",
                userImageURL = "userImageURL"
            )
        )
    )
}