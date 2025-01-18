package com.albertheijn.rijksmuseumassignment.data.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter(name = "key", value = apiKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(url = newUrl)
            .build()

        return chain.proceed(request = newRequest)
    }
}
