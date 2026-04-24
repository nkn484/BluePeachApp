package com.bluepeach.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.material3.Scaffold
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bluepeach.app.core.ui.components.BluePeachBottomNavBar
import com.bluepeach.app.feature.account.AccountScreen
import com.bluepeach.app.feature.cart.CartScreen
import com.bluepeach.app.feature.home.HomeScreen
import com.bluepeach.app.feature.productdetail.ProductDetailScreen
import com.bluepeach.app.feature.products.ProductListScreen
import com.bluepeach.app.feature.splash.SplashScreen

@Composable
fun BluePeachNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (AppRoute.isRootRoute(currentRoute)) {
                BluePeachBottomNavBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navigateToRootRoute(navController, route)
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.SPLASH,
            modifier = Modifier
                .padding(innerPadding)
                .clipToBounds()
        ) {
            composable(AppRoute.SPLASH) {
                SplashScreen(
                    onSplashCompleted = {
                        navController.navigateSafely(AppRoute.HOME) {
                            popUpTo(AppRoute.SPLASH) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(AppRoute.HOME) {
                HomeScreen(
                    onOpenProducts = { navigateToRootRoute(navController, AppRoute.PRODUCTS) },
                    onOpenProduct = { productId ->
                        navController.navigateSafely(AppRoute.productDetail(productId))
                    }
                )
            }

            composable(AppRoute.PRODUCTS) {
                ProductListScreen(
                    onOpenProduct = { productId ->
                        navController.navigateSafely(AppRoute.productDetail(productId))
                    }
                )
            }

            composable(
                route = AppRoute.PRODUCT_DETAIL,
                arguments = listOf(navArgument(AppRoute.PRODUCT_ID_ARG) { type = NavType.StringType })
            ) {
                ProductDetailScreen(
                    onBack = { navController.popBackStackSafely() },
                    onOpenRingMeasurement = { _ ->
                        // Integration seam: replace this with HandMeasure module navigation in a later step.
                    }
                )
            }

            composable(AppRoute.CART) {
                CartScreen()
            }

            composable(AppRoute.ACCOUNT) {
                AccountScreen()
            }
        }
    }
}

private fun navigateToRootRoute(
    navController: NavHostController,
    route: String
) {
    navController.navigateSafely(route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(AppRoute.HOME) {
            saveState = true
        }
    }
}
