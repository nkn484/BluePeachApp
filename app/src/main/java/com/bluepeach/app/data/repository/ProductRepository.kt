package com.bluepeach.app.data.repository

import com.bluepeach.app.data.model.Category
import com.bluepeach.app.data.model.CustomerReview
import com.bluepeach.app.data.model.HomeBanner
import com.bluepeach.app.data.model.Product

interface ProductRepository {
    suspend fun getProducts(
        query: String? = null,
        categoryId: String? = null,
        sort: String? = null,
        limit: Int = 40,
        offset: Int = 0
    ): Result<List<Product>>

    suspend fun getProductDetail(productId: String): Result<Product?>
    suspend fun getCategories(): Result<List<Category>>
    suspend fun getFeaturedReviews(limit: Int = 6): Result<List<CustomerReview>>
    suspend fun getHomeBanner(): Result<HomeBanner?>
}
