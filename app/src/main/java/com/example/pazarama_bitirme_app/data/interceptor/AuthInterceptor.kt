package com.example.pazarama_bitirme_app.data.interceptor

import com.example.pazarama_bitirme_app.data.remote.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()

        val newUrl = requestBuilder.url.newBuilder()

            .build()

        val newRequest = requestBuilder.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}