package com.bluepeach.app.core.navigation

import android.net.Uri

object AppRoute {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val PRODUCTS = "products"
    const val PRODUCT_ID_ARG = "productId"
    const val PRODUCT_DETAIL = "product-detail/{$PRODUCT_ID_ARG}"
    const val CART = "cart"
    const val ACCOUNT = "account"
    val ROOT_ROUTES = setOf(HOME, PRODUCTS, CART, ACCOUNT)

    fun productDetail(productId: String): String = "product-detail/${Uri.encode(productId)}"

    fun isRootRoute(route: String?): Boolean = route in ROOT_ROUTES
}
