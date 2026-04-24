package com.bluepeach.app.core.navigation

import android.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

private const val NAV_TAG = "BluePeachNav"

fun NavHostController.navigateSafely(
    route: String,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    if (currentDestination?.route == route) return

    runCatching {
        navigate(route, builder)
    }.onFailure { throwable ->
        Log.e(NAV_TAG, "Failed to navigate to route=$route", throwable)
    }
}

fun NavHostController.popBackStackSafely(): Boolean {
    return runCatching { popBackStack() }
        .onFailure { throwable ->
            Log.e(NAV_TAG, "Failed to pop back stack", throwable)
        }
        .getOrDefault(false)
}
