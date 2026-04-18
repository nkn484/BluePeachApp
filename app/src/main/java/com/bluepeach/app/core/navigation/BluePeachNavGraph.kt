package com.bluepeach.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bluepeach.app.core.common.SampleStorefrontData
import com.bluepeach.app.feature.account.AccountScreen
import com.bluepeach.app.feature.cart.CartScreen
import com.bluepeach.app.feature.home.HomeScreen
import com.bluepeach.app.feature.productdetail.ProductDetailScreen
import com.bluepeach.app.feature.products.ProductListScreen
import com.bluepeach.app.feature.splash.SplashScreen

@Composable
fun BluePeachNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.SPLASH
    ) {
        composable(AppRoute.SPLASH) {
            SplashScreen(
                onSplashCompleted = {
                    navController.navigate(AppRoute.HOME) {
                        popUpTo(AppRoute.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.HOME) {
            HomeScreen(
                onOpenProducts = { navController.navigate(AppRoute.PRODUCTS) },
                onOpenProduct = { productId ->
                    navController.navigate(AppRoute.productDetail(productId))
                },
                onOpenCart = { navController.navigate(AppRoute.CART) },
                onOpenAccount = { navController.navigate(AppRoute.ACCOUNT) }
            )
        }

        composable(AppRoute.PRODUCTS) {
            ProductListScreen(
                products = SampleStorefrontData.products,
                onBack = { navController.popBackStack() },
                onOpenProduct = { productId ->
                    navController.navigate(AppRoute.productDetail(productId))
                }
            )
        }

        composable(
            route = AppRoute.PRODUCT_DETAIL,
            arguments = listOf(navArgument(AppRoute.PRODUCT_ID_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(AppRoute.PRODUCT_ID_ARG).orEmpty()
            ProductDetailScreen(
                productId = productId,
                onBack = { navController.popBackStack() },
                onOpenRingMeasurement = { _ ->
                    // Integration seam: replace this with HandMeasure module navigation in a later step.
                }
            )
        }

        composable(AppRoute.CART) {
            CartScreen(onBack = { navController.popBackStack() })
        }

        composable(AppRoute.ACCOUNT) {
            AccountScreen(onBack = { navController.popBackStack() })
        }
    }
}
