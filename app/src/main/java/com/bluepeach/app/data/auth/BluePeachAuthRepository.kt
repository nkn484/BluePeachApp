package com.bluepeach.app.data.auth

import com.bluepeach.app.core.network.BluePeachSupabaseClient
import io.github.jan.supabase.gotrue.SignOutScope
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

interface BluePeachAuthRepository {
    suspend fun signInWithEmailPassword(email: String, password: String): Result<Unit>
    suspend fun signUpWithEmailPassword(email: String, password: String): Result<Unit>
    suspend fun signOut(): Result<Unit>
    fun currentAccessToken(): String?
    fun isSignedIn(): Boolean = !currentAccessToken().isNullOrBlank()
}

object SupabaseBluePeachAuthRepository : BluePeachAuthRepository {
    override suspend fun signInWithEmailPassword(email: String, password: String): Result<Unit> {
        return runCatching {
            BluePeachSupabaseClient.client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
        }
    }

    override suspend fun signUpWithEmailPassword(email: String, password: String): Result<Unit> {
        return runCatching {
            BluePeachSupabaseClient.client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return runCatching {
            BluePeachSupabaseClient.client.auth.signOut(SignOutScope.LOCAL)
        }
    }

    override fun currentAccessToken(): String? {
        return BluePeachSupabaseClient.client.auth.currentAccessTokenOrNull()
    }
}
