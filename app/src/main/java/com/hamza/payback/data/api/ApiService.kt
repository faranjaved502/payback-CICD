package com.hamza.payback.data.api

import com.hamza.payback.data.feed.network.response.FeedsResultResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    fun getFeedsList(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") image_type: String,
        @Query("pretty") pretty: Boolean
    ): Call<FeedsResultResponseDTO>
}
