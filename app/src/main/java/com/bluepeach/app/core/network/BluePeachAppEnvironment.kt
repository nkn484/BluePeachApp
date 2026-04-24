package com.bluepeach.app.core.network

import android.content.Context

object BluePeachAppEnvironment {
    @Volatile
    private var appContext: Context? = null

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun contextOrNull(): Context? = appContext
}
