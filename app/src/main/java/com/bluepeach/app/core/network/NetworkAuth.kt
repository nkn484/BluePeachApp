package com.bluepeach.app.core.network

/**
 * Mirrors web behavior in `frontend/src/lib/api.ts`:
 * attach Supabase access token as `Authorization: Bearer <token>` for protected routes.
 */
interface AccessTokenProvider {
    suspend fun getAccessToken(): String?
}

object EmptyAccessTokenProvider : AccessTokenProvider {
    override suspend fun getAccessToken(): String? = null
}

enum class AuthMode {
    PUBLIC,
    AUTH_REQUIRED
}

suspend fun buildJsonHeaders(
    authMode: AuthMode,
    tokenProvider: AccessTokenProvider,
    extraHeaders: Map<String, String> = emptyMap()
): Map<String, String> {
    val headers = linkedMapOf(
        "Content-Type" to "application/json"
    )

    if (authMode == AuthMode.AUTH_REQUIRED) {
        tokenProvider.getAccessToken()
            ?.takeIf { it.isNotBlank() }
            ?.let { token ->
                headers["Authorization"] = "Bearer $token"
            }
    }

    headers.putAll(extraHeaders)
    return headers
}
