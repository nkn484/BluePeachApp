package com.bluepeach.app.feature.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.SupportAgent
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.core.ui.components.BluePeachInfoBadge
import com.bluepeach.app.core.ui.components.BluePeachSectionHeader
import com.bluepeach.app.core.ui.components.BluePeachSupportRow
import com.bluepeach.app.core.ui.components.BluePeachTopBar

@Composable
fun AccountScreen(onBack: () -> Unit) {
    val entries = listOf(
        AccountEntry("Profile", "Edit your personal profile details", Icons.Rounded.Person),
        AccountEntry("Addresses", "Manage shipping addresses", Icons.Rounded.LocationOn),
        AccountEntry("Orders", "Track and review your order history", Icons.Rounded.ReceiptLong),
        AccountEntry("Notifications", "Shop updates and delivery alerts", Icons.Rounded.Notifications),
        AccountEntry("Wishlist", "Saved favorites and gift ideas", Icons.Rounded.VolunteerActivism),
        AccountEntry("Security", "Password and account protection", Icons.Rounded.Lock)
    )

    Scaffold(
        topBar = { BluePeachTopBar(title = "Account", onBack = onBack) },
        containerColor = BluePeachColors.surfacePlain
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(BluePeachColors.surfacePlain)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BluePeachSectionHeader(
                label = "Blue Peach account",
                title = "Customer profile center",
                description = "Shopper-focused account area aligned with web profile, addresses, orders, and security."
            )

            Surface(
                shape = MaterialTheme.shapes.large,
                color = BluePeachColors.surfaceWarm,
                border = androidx.compose.foundation.BorderStroke(1.dp, BluePeachColors.borderSoft)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Minh Anh Nguyen",
                        style = MaterialTheme.typography.titleLarge,
                        color = BluePeachColors.textPrimary
                    )
                    Text(
                        text = "minhanh@bluepeach.example",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BluePeachColors.textSecondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        BluePeachInfoBadge("Member since 2026")
                        BluePeachInfoBadge("Customer")
                    }
                }
            }

            Surface(
                shape = MaterialTheme.shapes.large,
                color = BluePeachColors.surfaceCard,
                border = androidx.compose.foundation.BorderStroke(1.dp, BluePeachColors.borderSoft),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Account overview",
                        style = MaterialTheme.typography.titleMedium,
                        color = BluePeachColors.textPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Profile, addresses, orders, wishlist, notifications, and security.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BluePeachColors.textSecondary
                    )
                }
            }

            entries.forEach { entry ->
                AccountEntryRow(entry = entry)
            }

            BluePeachSupportRow(
                title = "Need help with your order?",
                subtitle = "Contact support for delivery, product, or account assistance."
            )
        }
    }
}

private data class AccountEntry(
    val title: String,
    val subtitle: String,
    val icon: ImageVector
)

@Composable
private fun AccountEntryRow(entry: AccountEntry) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = BluePeachColors.surfaceCard,
        border = androidx.compose.foundation.BorderStroke(1.dp, BluePeachColors.borderSoft)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = entry.icon,
                contentDescription = null,
                tint = BluePeachColors.textPrimary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = entry.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = BluePeachColors.textPrimary
                )
                Text(
                    text = entry.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BluePeachColors.textSecondary
                )
            }
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = BluePeachColors.textSecondary
            )
        }
    }
}
