package com.example.animemangatoon.di

import com.example.animemangatoon.data.remote.NetworkApi
import com.example.animemangatoon.data.repository.Repository
import com.example.animemangatoon.utils.Constants.API_HOST
import com.example.animemangatoon.utils.Constants.API_KEY
import com.example.animemangatoon.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("x-rapidapi-key", API_KEY)  // Replace API_KEY with your actual API key
                    .header("x-rapidapi-host", API_HOST) // Replace API_HOST with your actual API host
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: NetworkApi): Repository {
        return Repository(api)
    }
}