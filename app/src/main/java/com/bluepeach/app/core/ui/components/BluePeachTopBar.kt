package com.bluepeach.app.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bluepeach.app.core.ui.BluePeachColors

@Composable
fun BluePeachTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    onCart: (() -> Unit)? = null,
    onAccount: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BluePeachColors.surfacePlain)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Quay lại",
                        tint = BluePeachColors.textPrimary
                    )
                }
            } else {
                Box(modifier = Modifier.width(48.dp))
            }

            if (onBack == null) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "BLUE PEACH",
                        style = MaterialTheme.typography.labelMedium,
                        color = BluePeachColors.textTertiary
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = BluePeachColors.textPrimary
                    )
                }
            } else {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BluePeachColors.textPrimary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.width(96.dp)
            ) {
                if (onAccount != null) {
                    IconButton(onClick = onAccount) {
                        Icon(
                            imageVector = Icons.Rounded.AccountCircle,
                            contentDescription = "Tài khoản",
                            tint = BluePeachColors.textPrimary
                        )
                    }
                }
                if (onAccount == null && onCart == null) {
                    Box(modifier = Modifier.size(48.dp))
                }
                if (onCart != null) {
                    IconButton(onClick = onCart) {
                        Icon(
                            imageVector = Icons.Rounded.ShoppingBag,
                            contentDescription = "Giỏ hàng",
                            tint = BluePeachColors.textPrimary
                        )
                    }
                }
            }
        }
        HorizontalDivider(color = BluePeachColors.borderSoft)
    }
}
