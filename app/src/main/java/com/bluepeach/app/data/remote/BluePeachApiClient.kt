package com.bluepeach.app.data.remote

import com.bluepeach.app.BuildConfig
import com.bluepeach.app.core.network.BluePeachAuthInterceptor
import com.bluepeach.app.core.network.NetworkConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
object BluePeachApiClient {
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        coerceInputValues = true
    }

    private val okHttpClient: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        OkHttpClient.Builder()
            .addInterceptor(BluePeachAuthInterceptor())
            .addInterceptor(logging)
            .build()
    }

    val service: BluePeachApiService by lazy {
        createService()
    }

    private fun createService(): BluePeachApiService {
        return Retrofit.Builder()
            .baseUrl(NetworkConfig.backendBaseUrl.ensureTrailingSlash())
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(BluePeachApiService::class.java)
    }
}

private fun String.ensureTrailingSlash(): String =
    if (endsWith("/")) this else "$this/"
