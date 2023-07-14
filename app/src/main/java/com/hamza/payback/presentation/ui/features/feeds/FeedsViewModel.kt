package com.hamza.payback.presentation.ui.features.feeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.payback.data.feed.domain.FeedData
import com.hamza.payback.domain.usecases.feeds.FeedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val feedsUseCase: FeedsUseCase
) : ViewModel() {

     private val _feedList: MutableLiveData<FeedData> = MutableLiveData()
     private val _showLoader: MutableLiveData<Boolean> = MutableLiveData(false)

    val feedList: MutableLiveData<FeedData> = _feedList
    val showLoader: MutableLiveData<Boolean> = _showLoader


    fun getFeeds(
        key: String,
        query: String,
        imageType: String,
        pretty: Boolean
    ) {
        _showLoader.value = false
        viewModelScope.launch {
            val result = feedsUseCase.execute(
                FeedsUseCase.Params(key, query, imageType, pretty)
            )
            when (result) {
                is FeedsUseCase.Result.Success -> {
                    _showLoader.value = false
                    _feedList.value = result.feeds
                }
                is FeedsUseCase.Result.Error -> {
                    _showLoader.value = false
                }
            }
        }
    }
}