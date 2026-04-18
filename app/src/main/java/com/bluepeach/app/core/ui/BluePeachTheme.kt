package com.bluepeach.app.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val BluePeachColorScheme = lightColorScheme(
    primary = BluePeachColors.primary,
    onPrimary = BluePeachColors.surfacePlain,
    secondary = BluePeachColors.accent,
    background = BluePeachColors.surfacePlain,
    surface = BluePeachColors.surfaceCard,
    onSurface = BluePeachColors.textPrimary,
    onSurfaceVariant = BluePeachColors.textSecondary,
    outline = BluePeachColors.borderSoft,
    error = BluePeachColors.danger
)

@Composable
fun BluePeachTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalBluePeachSpacing provides BluePeachSpacing()
    ) {
        MaterialTheme(
            colorScheme = BluePeachColorScheme,
            typography = BluePeachTypography,
            shapes = BluePeachShapes,
            content = content
        )
    }
}
