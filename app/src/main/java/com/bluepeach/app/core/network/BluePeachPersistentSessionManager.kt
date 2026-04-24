package com.bluepeach.app.core.network

import android.content.Context
import io.github.jan.supabase.gotrue.SessionManager
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BluePeachPersistentSessionManager(
    context: Context,
    private val ttlMillis: Long = SESSION_TTL_MILLIS
) : SessionManager {
    private val appPreferences = context.applicationContext.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        encodeDefaults = true
    }

    override suspend fun saveSession(session: UserSession) = withContext(Dispatchers.IO) {
        appPreferences.edit()
            .putString(KEY_SESSION, json.encodeToString(session))
            .putLong(KEY_SAVED_AT, Clock.System.now().toEpochMilliseconds())
            .apply()
    }

    override suspend fun loadSession(): UserSession? = withContext(Dispatchers.IO) {
        val savedAt = appPreferences.getLong(KEY_SAVED_AT, 0L)
        val rawSession = appPreferences.getString(KEY_SESSION, null)
            ?: return@withContext null

        if (savedAt <= 0L || Clock.System.now().toEpochMilliseconds() - savedAt > ttlMillis) {
            deleteSession()
            return@withContext null
        }

        runCatching {
            json.decodeFromString<UserSession>(rawSession)
        }.getOrElse {
            deleteSession()
            null
        }
    }

    override suspend fun deleteSession() = withContext(Dispatchers.IO) {
        appPreferences.edit()
            .remove(KEY_SESSION)
            .remove(KEY_SAVED_AT)
            .apply()
    }

    companion object {
        private const val PREFS_NAME = "blue_peach_supabase_session"
        private const val KEY_SESSION = "session_json"
        private const val KEY_SAVED_AT = "saved_at"
        private const val SESSION_TTL_MILLIS = 24L * 60L * 60L * 1000L
    }
}
