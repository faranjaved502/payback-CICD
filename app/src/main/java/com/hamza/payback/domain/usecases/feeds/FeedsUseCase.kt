package com.hamza.payback.domain.usecases.feeds

import com.hamza.payback.data.feed.domain.FeedData
import com.hamza.payback.mvvm.usecase.UseCase

interface FeedsUseCase: UseCase<FeedsUseCase.Params, FeedsUseCase.Result> {

    data class Params(
        val key: String,
        val query: String,
        val imageType: String,
        val pretty: Boolean,
    )

    sealed class Result {
        data class Success(val feeds: FeedData) : Result()
        data class Error(val message: String) : Result()
    }
}