package com.bluepeach.app.core.network

import android.util.Log

object RuntimeConfigValidator {
    private const val TAG = "BluePeachConfig"

    fun validate() {
        if (!NetworkConfig.isBackendConfigured) {
            Log.w(TAG, "Missing API base URL. Set BLUE_PEACH_API_BASE_URL in local.properties.")
        }

        if (!NetworkConfig.isSupabaseConfigured) {
            Log.w(
                TAG,
                "Missing Supabase config. Set BLUE_PEACH_SUPABASE_URL and BLUE_PEACH_SUPABASE_ANON_KEY."
            )
        }

        if (!NetworkConfig.backendBaseUrl.endsWith("/api")) {
            Log.w(
                TAG,
                "API base URL should end with /api to match the shared web/backend contract."
            )
        }
    }
}
