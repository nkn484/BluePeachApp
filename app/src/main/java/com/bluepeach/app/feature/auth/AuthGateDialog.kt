package com.bluepeach.app.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.core.ui.components.BluePeachPrimaryButton

@Composable
fun AuthGateDialog(
    onLoginClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            color = BluePeachColors.surfaceCard,
            border = androidx.compose.foundation.BorderStroke(1.dp, BluePeachColors.borderSoft)
        ) {
            Box(
                modifier = Modifier.padding(18.dp)
            ) {
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(28.dp)
                        .alpha(0.45f)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Đóng",
                        tint = BluePeachColors.textPrimary
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, end = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Xin hãy đăng nhập để thực hiện thao tác này",
                        style = MaterialTheme.typography.titleLarge,
                        color = BluePeachColors.textPrimary
                    )
                    BluePeachPrimaryButton(
                        text = "Đăng nhập",
                        onClick = onLoginClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
