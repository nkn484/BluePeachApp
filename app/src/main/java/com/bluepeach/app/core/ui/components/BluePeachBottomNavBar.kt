package com.bluepeach.app.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material.icons.rounded.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bluepeach.app.core.navigation.AppRoute
import com.bluepeach.app.core.ui.BluePeachColors

@Composable
fun BluePeachBottomNavBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem(AppRoute.HOME, "Trang chủ", Icons.Rounded.Home),
        BottomNavItem(AppRoute.PRODUCTS, "Sản phẩm", Icons.Rounded.Widgets),
        BottomNavItem(AppRoute.CART, "Giỏ hàng", Icons.Rounded.ShoppingBag),
        BottomNavItem(AppRoute.ACCOUNT, "Tài khoản", Icons.Rounded.AccountCircle)
    )

    Surface(
        border = BorderStroke(1.dp, BluePeachColors.borderSoft),
        color = BluePeachColors.surfaceCard,
        shadowElevation = 6.dp
    ) {
        NavigationBar(
            containerColor = BluePeachColors.surfaceCard,
            tonalElevation = 0.dp
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route
                NavigationBarItem(
                    selected = selected,
                    onClick = { onNavigate(item.route) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label
                        )
                    },
                    label = { Text(text = item.label) },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = BluePeachColors.surfaceWarm,
                        selectedIconColor = BluePeachColors.textPrimary,
                        selectedTextColor = BluePeachColors.textPrimary,
                        unselectedIconColor = BluePeachColors.textTertiary,
                        unselectedTextColor = BluePeachColors.textTertiary
                    )
                )
            }
        }
    }
}

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)
