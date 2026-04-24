package com.bluepeach.app

import android.app.Application
import com.bluepeach.app.core.network.BluePeachAppEnvironment

class BluePeachApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BluePeachAppEnvironment.init(this)
    }
}
