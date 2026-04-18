package com.bluepeach.app

import androidx.compose.runtime.Composable
import com.bluepeach.app.core.navigation.BluePeachNavGraph
import com.bluepeach.app.core.ui.BluePeachTheme

@Composable
fun BluePeachApp() {
    BluePeachTheme {
        BluePeachNavGraph()
    }
}
