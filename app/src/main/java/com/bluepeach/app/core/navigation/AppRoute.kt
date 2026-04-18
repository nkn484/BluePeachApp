package com.bluepeach.app.core.navigation

object AppRoute {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val PRODUCTS = "products"
    const val PRODUCT_ID_ARG = "productId"
    const val PRODUCT_DETAIL = "product-detail/{$PRODUCT_ID_ARG}"
    const val CART = "cart"
    const val ACCOUNT = "account"

    fun productDetail(productId: String): String = "product-detail/$productId"
}
