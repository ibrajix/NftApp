package com.ibrajix.nftapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibrajix.nftapp.network.ApiDataSource
import com.ibrajix.nftapp.network.ApiService
import com.ibrajix.nftapp.utilis.EndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesBaseUrl() = EndPoints.BASE_URL

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson) : Retrofit {
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