package com.hamza.payback.di

import com.hamza.payback.data.services.FeedService
import com.hamza.payback.domain.usecases.feeds.FeedsUseCase
import com.hamza.payback.domain.usecases.feeds.FeedsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class UseCaseModule {

    @Provides
    @ActivityRetainedScoped
    fun provideUserUseCase(userService: FeedService): FeedsUseCase = FeedsUseCaseImpl(userService)
}