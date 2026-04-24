package com.bluepeach.app.core.network

/**
 * Endpoint contract mirrored from the shared Node backend used by web and mobile.
 * Backend is mounted at `/api` (already included in [NetworkConfig.backendBaseUrl]).
 */
object BackendApiContract {
    const val HEALTH = "/health"

    object Products {
        const val ROOT = "/products"
        fun detail(productId: String): String = "$ROOT/$productId"
        fun reviews(productId: String): String = "$ROOT/$productId/reviews"
        fun related(productId: String): String = "$ROOT/$productId/related"
    }

    object Categories {
        const val ROOT = "/categories"
    }

    object Banners {
        const val HOME = "/banners/home"
    }

    object Reviews {
        const val FEATURED = "/reviews/featured"
    }

    object Collections {
        const val ROOT = "/collections"
        fun bySlug(slug: String): String = "$ROOT/$slug"
    }

    object Orders {
        const val ROOT = "/orders"
        fun detail(orderId: String): String = "$ROOT/$orderId"
        fun updateStatus(orderId: String): String = "$ROOT/$orderId/status"
    }

    object Coupons {
        const val VALIDATE = "/coupons/validate"
    }

    object Wishlist {
        const val ROOT = "/wishlist"
        fun delete(productId: String): String = "$ROOT/$productId"
    }

    object Notifications {
        const val ROOT = "/notifications"
        const val MARK_READ = "/notifications/mark-read"
        const val MARK_ALL_READ = "/notifications/mark-all-read"
        fun delete(notificationId: String): String = "$ROOT/$notificationId"
    }

    object Support {
        const val SESSION = "/support/session"
        const val MESSAGES = "/support/messages"
    }

    object Payments {
        const val VNPAY_CREATE = "/payments/vnpay/create"
        const val VNPAY_RETURN = "/payments/vnpay/return"
        const val VNPAY_IPN = "/payments/vnpay/ipn"
    }
}

object BackendQueryKeys {
    const val QUERY = "q"
    const val CATEGORY_ID = "categoryId"
    const val COLLECTION = "collection"
    const val SORT = "sort"
    const val LIMIT = "limit"
    const val OFFSET = "offset"
    const val MIN_PRICE = "minPrice"
    const val MAX_PRICE = "maxPrice"
}
