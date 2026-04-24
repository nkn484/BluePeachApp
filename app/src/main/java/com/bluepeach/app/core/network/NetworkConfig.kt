package com.bluepeach.app.core.network

import com.bluepeach.app.BuildConfig

object NetworkConfig {
    val backendBaseUrl: String = BuildConfig.API_BASE_URL.trim().trimEnd('/')
    val supabaseBaseUrl: String = BuildConfig.SUPABASE_URL.trim().trimEnd('/')
    val supabaseAnonKey: String = BuildConfig.SUPABASE_ANON_KEY.trim()

    val isBackendConfigured: Boolean
        get() = backendBaseUrl.isNotBlank()

    val isSupabaseConfigured: Boolean
        get() = supabaseBaseUrl.isNotBlank() && supabaseAnonKey.isNotBlank()

    fun backendUrl(path: String): String {
        val normalizedPath = if (path.startsWith("/")) path else "/$path"
        return backendBaseUrl + normalizedPath
    }
}
