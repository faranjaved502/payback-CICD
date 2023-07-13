package com.hamza.payback.di

import com.hamza.payback.data.api.ApiService
import com.hamza.payback.data.services.FeedService
import com.hamza.payback.data.services.FeedServiceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    val CONTENT_TYPE = "Content-Type"
    val CONTENT_TYPE_VALUE = "application/json"
    val BASE_URL = "https://pixabay.com/"

    var headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
            .header("Accept", "application/json")
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

    /*@Singleton
    @Provides
    @LoginRetrofitInstance
    fun provideLoginRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
*/
    @Singleton
    @Provides
    fun provideAppRetrofitInstance(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

   /* @Singleton
    @Provides
    @LoginApiService
    fun provideLoginApiService(@LoginRetrofitInstance retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)*/

    @Provides
    fun provideFeedService(api: ApiService): FeedService =
        FeedServiceImpl(api)
}

/*
@Qualifier
annotation class LoginRetrofitInstance

@Qualifier
annotation class AppRetrofitInstance

@Qualifier
annotation class LoginApiService

@Qualifier
annotation class AppApiService
*/