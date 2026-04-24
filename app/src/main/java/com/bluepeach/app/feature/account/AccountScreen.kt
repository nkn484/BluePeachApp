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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
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
fun AccountScreen(onBack: (() -> Unit)? = null) {
    val entries = listOf(
        AccountEntry("Hồ sơ", "Chỉnh sửa thông tin cá nhân", Icons.Rounded.Person),
        AccountEntry("Địa chỉ", "Quản lý địa chỉ nhận hàng", Icons.Rounded.LocationOn),
        AccountEntry("Đơn hàng", "Theo dõi lịch sử mua hàng", Icons.AutoMirrored.Rounded.ReceiptLong),
        AccountEntry("Thông báo", "Cập nhật đơn hàng và ưu đãi", Icons.Rounded.Notifications),
        AccountEntry("Yêu thích", "Danh sách sản phẩm đã lưu", Icons.Rounded.VolunteerActivism),
        AccountEntry("Bảo mật", "Mật khẩu và bảo vệ tài khoản", Icons.Rounded.Lock)
    )

    Scaffold(
        topBar = { BluePeachTopBar(title = "Tài khoản", onBack = onBack) },
        containerColor = BluePeachColors.surfacePlain
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(BluePeachColors.surfacePlain)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BluePeachSectionHeader(
                label = "Blue Peach",
                title = "Không gian tài khoản",
                description = "Hồ sơ, địa chỉ, đơn hàng, yêu thích, thông báo và bảo mật.",
                centered = false
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
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        BluePeachInfoBadge("Thành viên từ 2026")
                        BluePeachInfoBadge("Khách hàng")
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
                        text = "Tổng quan tài khoản",
                        style = MaterialTheme.typography.titleMedium,
                        color = BluePeachColors.textPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Khu vực này sẽ kết nối với Supabase Auth ở bước tích hợp tài khoản.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BluePeachColors.textSecondary
                    )
                }
            }

            entries.forEach { entry ->
                AccountEntryRow(entry = entry)
            }

            BluePeachSupportRow(
                title = "Cần hỗ trợ đơn hàng?",
                subtitle = "Liên hệ Blue Peach để được hỗ trợ giao hàng, sản phẩm hoặc tài khoản."
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
            Spacer(modifier = Modifier.width(8.dp))
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
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = BluePeachColors.textSecondary
            )
        }
    }
}
