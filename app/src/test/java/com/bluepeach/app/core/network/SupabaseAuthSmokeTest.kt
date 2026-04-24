package com.bluepeach.app.core.network

import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.SignOutScope
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import com.bluepeach.app.core.test.MainDispatcherRule

class SupabaseAuthSmokeTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun emailPasswordSignInProvidesAccessToken() = runBlocking {
        val supabase = BluePeachSupabaseClient.client

        supabase.auth.signInWith(Email) {
            email = "mobile@bluepeach.com"
            password = "bluepeach123"
        }

        val session = supabase.auth.currentSessionOrNull()
        assertNotNull(session)
        assertTrue(session!!.accessToken.isNotBlank())

        supabase.auth.signOut(SignOutScope.LOCAL)
    }
}
