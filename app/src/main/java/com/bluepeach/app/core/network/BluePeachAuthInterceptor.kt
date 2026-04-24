package com.bluepeach.app.core.network

import java.io.IOException
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class BluePeachAuthInterceptor(
    private val tokenProvider: AccessTokenProvider = SupabaseSessionAccessTokenProvider()
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenProvider.getAccessToken() }.orEmpty()
        val request = if (token.isBlank()) {
            chain.request()
        } else {
            chain.request()
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        }

        return chain.proceed(request)
    }
}
