package com.albertheijn.rijksmuseumassignment.application.di

import com.albertheijn.rijksmuseumassignment.data.network.ApiKeyInterceptor
import com.albertheijn.rijksmuseumassignment.data.network.RijksmuseumApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val API_KEY = "0fiuZFh4"
    private const val BASE_URL = "https://www.rijksmuseum.nl/api/"
    private const val TIMEOUT_IN_SECONDS = 1L

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(timeout = TIMEOUT_IN_SECONDS, unit = TimeUnit.SECONDS)
        .readTimeout(timeout = TIMEOUT_IN_SECONDS, unit = TimeUnit.SECONDS)
        .addInterceptor(interceptor = ApiKeyInterceptor(API_KEY))
        .addInterceptor(
            interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory("application/json; charset=UTF8".toMediaType())
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRijksmuseumApi(retrofit: Retrofit): RijksmuseumApi = retrofit.create()
}
