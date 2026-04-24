package com.bluepeach.app.core.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.MemoryCodeVerifierCache
import io.github.jan.supabase.gotrue.MemorySessionManager
import io.github.jan.supabase.gotrue.auth

object BluePeachSupabaseClient {
    val client by lazy {
        val appContext = BluePeachAppEnvironment.contextOrNull()
        createSupabaseClient(
            supabaseUrl = NetworkConfig.supabaseBaseUrl,
            supabaseKey = NetworkConfig.supabaseAnonKey
        ) {
            install(Auth) {
                sessionManager = appContext?.let { BluePeachPersistentSessionManager(it) }
                    ?: MemorySessionManager()
                codeVerifierCache = MemoryCodeVerifierCache()
                autoLoadFromStorage = appContext != null
                autoSaveToStorage = appContext != null
                enableLifecycleCallbacks = false
            }
        }
    }
}

class SupabaseSessionAccessTokenProvider : AccessTokenProvider {
    override suspend fun getAccessToken(): String? {
        return runCatching {
            BluePeachSupabaseClient.client.auth.currentAccessTokenOrNull()
        }.getOrNull()
    }
}
