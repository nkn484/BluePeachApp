package com.bluepeach.app.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bluepeach.app.data.auth.SupabaseBluePeachAuthRepository
import com.bluepeach.app.core.ui.components.BluePeachBottomNavBar
import com.bluepeach.app.feature.account.AccountScreen
import com.bluepeach.app.feature.auth.AuthGateDialog
import com.bluepeach.app.feature.auth.LoginScreen
import com.bluepeach.app.feature.auth.RegisterScreen
import com.bluepeach.app.feature.cart.CartScreen
import com.bluepeach.app.feature.home.HomeScreen
import com.bluepeach.app.feature.productdetail.ProductDetailScreen
import com.bluepeach.app.feature.products.ProductListScreen
import com.bluepeach.app.feature.splash.SplashScreen

private val protectedRootRoutes = setOf(AppRoute.CART, AppRoute.ACCOUNT)

private data class AuthGateRequest(
    val onLoginClick: () -> Unit
)

@Composable
fun BluePeachNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val authRepository = SupabaseBluePeachAuthRepository

    var authGateRequest by remember { mutableStateOf<AuthGateRequest?>(null) }
    var postLoginAction by remember { mutableStateOf<(() -> Unit)?>(null) }

    Scaffold(
        bottomBar = {
            if (AppRoute.isRootRoute(currentRoute)) {
                BluePeachBottomNavBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        if (route in protectedRootRoutes && !authRepository.isSignedIn()) {
                            postLoginAction = {
                                navigateToRootRoute(navController, route)
                            }
                            authGateRequest = AuthGateRequest(
                                onLoginClick = {
                                    authGateRequest = null
                                    navController.navigateSafely(AppRoute.LOGIN)
                                }
                            )
                        } else {
                            navigateToRootRoute(navController, route)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        Box {
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
                        requiresAuthForActions = !authRepository.isSignedIn(),
                        onRequireLogin = {
                            postLoginAction = {
                                navController.popBackStackSafely()
                            }
                            authGateRequest = AuthGateRequest(
                                onLoginClick = {
                                    authGateRequest = null
                                    navController.navigateSafely(AppRoute.LOGIN)
                                }
                            )
                        },
                        onOpenRingMeasurement = { _ ->
                            // HandMeasure integration seam for a later step.
                        }
                    )
                }

                composable(AppRoute.CART) {
                    CartScreen()
                }

                composable(AppRoute.ACCOUNT) {
                    AccountScreen(
                        onOpenLogin = {
                            postLoginAction = {
                                navController.navigateSafely(AppRoute.ACCOUNT) {
                                    popUpTo(AppRoute.LOGIN) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                            authGateRequest = AuthGateRequest(
                                onLoginClick = {
                                    authGateRequest = null
                                    navController.navigateSafely(AppRoute.LOGIN)
                                }
                            )
                        }
                    )
                }

                composable(AppRoute.LOGIN) {
                    LoginScreen(
                        onBack = { navController.popBackStackSafely() },
                        onLoginSuccess = {
                            val action = postLoginAction
                            postLoginAction = null
                            if (action != null) {
                                action()
                            } else {
                                navController.popBackStackSafely()
                            }
                        },
                        onOpenRegister = { navController.navigateSafely(AppRoute.REGISTER) }
                    )
                }

                composable(AppRoute.REGISTER) {
                    RegisterScreen(
                        onBack = { navController.popBackStackSafely() },
                        onRegisterSuccess = {
                            navController.navigateSafely(AppRoute.LOGIN) {
                                popUpTo(AppRoute.REGISTER) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }

            authGateRequest?.let { request ->
                AuthGateDialog(
                    onLoginClick = request.onLoginClick,
                    onDismiss = {
                        authGateRequest = null
                        postLoginAction = null
                    }
                )
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
