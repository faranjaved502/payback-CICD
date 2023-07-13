package com.hamza.payback.feature.feeds

import com.hamza.payback.data.feed.domain.FeedData
import com.hamza.payback.data.feed.domain.Feeds
import com.hamza.payback.domain.usecases.feeds.FeedsUseCase
import com.hamza.payback.framework.ViewModelBaseTest
import com.hamza.payback.presentation.ui.features.feeds.FeedsViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import kotlin.test.assertEquals

class FeedsViewModelTest: ViewModelBaseTest() {

    private lateinit var viewModel: FeedsViewModel

    @Mock
    lateinit var useCase: FeedsUseCase

    @Before
    fun setUp() {
        super.setUpBase()
        viewModel = FeedsViewModel(useCase)
    }

    @Test
    fun testGetFeeds() = runBlocking {
        //given
        val key = "mock_key"
        val query = "mock_query"
        val imageType = "mock_image_type"

        //when
        whenever(useCase.execute(FeedsUseCase.Params(key, query, imageType, true)))
            .thenReturn(FeedsUseCase.Result.Success(getMockFeedResponse()))

        //then
       viewModel.getFeeds(key, query, imageType, true)

        // Assert
        assertEquals(false, viewModel.showLoader.value)
        assertEquals(getMockFeedResponse(), viewModel.feedList.value)
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