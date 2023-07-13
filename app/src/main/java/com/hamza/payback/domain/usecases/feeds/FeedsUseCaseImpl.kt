package com.hamza.payback.domain.usecases.feeds

import com.hamza.payback.core.utils.isSuccess
import com.hamza.payback.data.services.FeedService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedsUseCaseImpl @Inject constructor(
    private val feedService: FeedService
): FeedsUseCase {


    override suspend fun execute(inputT: FeedsUseCase.Params): FeedsUseCase.Result {
        return withContext(Dispatchers.IO) {
            val result = feedService.getFeeds(inputT.key, inputT.query, inputT.imageType, inputT.pretty)
            if (result.isSuccess()) {
                val response = result.response
                if (response != null) {
                    FeedsUseCase.Result.Success(result.response)
                } else FeedsUseCase.Result.Error("Response is empty")
            } else {
                FeedsUseCase.Result.Error(result.error?.errorMessage ?: "")
            }
        }
    }
}