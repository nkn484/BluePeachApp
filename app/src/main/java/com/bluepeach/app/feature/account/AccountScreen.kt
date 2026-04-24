package com.bluepeach.app.feature.account

import androidx.compose.foundation.background
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.VolunteerActivism
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.core.ui.components.BluePeachInfoBadge
import com.bluepeach.app.core.ui.components.BluePeachPrimaryButton
import com.bluepeach.app.core.ui.components.BluePeachSecondaryButton
import com.bluepeach.app.core.ui.components.BluePeachSectionHeader
import com.bluepeach.app.core.ui.components.BluePeachSupportRow
import com.bluepeach.app.core.ui.components.BluePeachTopBar
import com.bluepeach.app.data.auth.BluePeachAuthRepository
import com.bluepeach.app.data.auth.SupabaseBluePeachAuthRepository
import kotlinx.coroutines.launch

@Composable
fun AccountScreen(
    onBack: (() -> Unit)? = null,
    onOpenLogin: () -> Unit = {},
    authRepository: BluePeachAuthRepository = SupabaseBluePeachAuthRepository
) {
    val entries = listOf(
        AccountEntry("Hồ sơ", "Chỉnh sửa thông tin cá nhân", Icons.Rounded.Person),
        AccountEntry("Địa chỉ", "Quản lý địa chỉ nhận hàng", Icons.Rounded.LocationOn),
        AccountEntry("Đơn hàng", "Theo dõi lịch sử mua hàng", Icons.AutoMirrored.Rounded.ReceiptLong),
        AccountEntry("Thông báo", "Cập nhật đơn hàng và ưu đãi", Icons.Rounded.Notifications),
        AccountEntry("Yêu thích", "Danh sách sản phẩm đã lưu", Icons.Rounded.VolunteerActivism),
        AccountEntry("Bảo mật", "Mật khẩu và bảo vệ tài khoản", Icons.Rounded.Lock)
    )
    val coroutineScope = rememberCoroutineScope()
    var authStateVersion by remember { mutableIntStateOf(0) }
    val isSignedIn = remember(authStateVersion) { authRepository.isSignedIn() }

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
                border = BorderStroke(1.dp, BluePeachColors.borderSoft)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (isSignedIn) "Đã kết nối Supabase Auth" else "Chưa đăng nhập",
                        style = MaterialTheme.typography.titleLarge,
                        color = BluePeachColors.textPrimary
                    )
                    Text(
                        text = if (isSignedIn) {
                            "Session token hiện có thể được dùng cho các API private."
                        } else {
                            "Đăng nhập để đồng bộ wishlist, thông báo và hỗ trợ."
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = BluePeachColors.textSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        BluePeachInfoBadge("Supabase")
                        BluePeachInfoBadge("Bearer token")
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    if (isSignedIn) {
                        BluePeachSecondaryButton(
                            text = "Đăng xuất",
                            onClick = {
                                coroutineScope.launch {
                                    authRepository.signOut()
                                    authStateVersion += 1
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        BluePeachPrimaryButton(
                            text = "Đăng nhập",
                            onClick = onOpenLogin,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            Surface(
                shape = MaterialTheme.shapes.large,
                color = BluePeachColors.surfaceCard,
                border = BorderStroke(1.dp, BluePeachColors.borderSoft),
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
                        text = "Khu vực này sẽ nối với Supabase Auth và dữ liệu hồ sơ người dùng ở bước tiếp theo.",
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
        border = BorderStroke(1.dp, BluePeachColors.borderSoft)
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
