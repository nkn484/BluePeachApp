package com.bluepeach.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bluepeach.app.core.network.RuntimeConfigValidator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RuntimeConfigValidator.validate()
        setContent {
            BluePeachApp()
        }
    }
}
