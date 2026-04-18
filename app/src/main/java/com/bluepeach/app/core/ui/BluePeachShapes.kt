package com.bluepeach.app.core.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

val BluePeachShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(28.dp)
)

@Immutable
data class BluePeachRadius(
    val pill: RoundedCornerShape = RoundedCornerShape(999.dp),
    val card: RoundedCornerShape = RoundedCornerShape(24.dp),
    val media: RoundedCornerShape = RoundedCornerShape(20.dp)
)
