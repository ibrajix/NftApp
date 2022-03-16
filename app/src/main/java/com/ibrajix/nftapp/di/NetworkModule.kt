package com.ibrajix.nftapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibrajix.nftapp.BuildConfig
import com.ibrajix.nftapp.network.ApiDataSource
import com.ibrajix.nftapp.network.ApiService
import com.ibrajix.nftapp.utilis.EndPoints
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesBaseUrl() = EndPoints.BASE_URL

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson) : Retrofit {
        val contentType = "application/json".toMediaType()
        val converterFactory = Json.asConverterFactory(contentType)
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder().also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(EndPoints.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiDataSource(apiService: ApiService) = ApiDataSource(apiService)

}