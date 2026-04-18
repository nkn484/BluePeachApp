package com.bluepeach.app.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bluepeach.app.core.ui.BluePeachColors
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashCompleted: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1100)
        onSplashCompleted()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BluePeachColors.surfaceWarm),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "BLUE PEACH",
            style = MaterialTheme.typography.displayMedium,
            color = BluePeachColors.textPrimary
        )
        Text(
            text = "Refined silver storefront",
            style = MaterialTheme.typography.bodyMedium,
            color = BluePeachColors.textSecondary
        )
    }
}
