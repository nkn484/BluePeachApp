package com.bluepeach.app.data.remote

import com.bluepeach.app.data.remote.dto.CategoryListResponseDto
import com.bluepeach.app.data.remote.dto.FeaturedReviewsResponseDto
import com.bluepeach.app.data.remote.dto.HomeBannerResponseDto
import com.bluepeach.app.data.remote.dto.ProductDetailDto
import com.bluepeach.app.data.remote.dto.ProductListResponseDto
import com.bluepeach.app.data.remote.dto.ProductReviewsResponseDto

/**
 * Remote seam for shared backend + Supabase wiring.
 *
 * Phase 1 keeps this contract intentionally small.
 * Phase 2 will implement real HTTP calls against endpoints from [com.bluepeach.app.core.network.BackendApiContract].
 */
interface BluePeachRemoteDataSource {
    suspend fun healthCheck(): Result<Unit>
    suspend fun getProducts(
        query: String? = null,
        categoryId: String? = null,
        sort: String? = null,
        limit: Int = 20,
        offset: Int = 0,
        minPrice: Int? = null,
        maxPrice: Int? = null
    ): Result<ProductListResponseDto>

    suspend fun getProductDetail(productId: String): Result<ProductDetailDto>
    suspend fun getRelatedProducts(productId: String, limit: Int = 4): Result<ProductListResponseDto>
    suspend fun getProductReviews(productId: String): Result<ProductReviewsResponseDto>
    suspend fun getCategories(): Result<CategoryListResponseDto>
    suspend fun getHomeBanner(): Result<HomeBannerResponseDto>
    suspend fun getFeaturedReviews(limit: Int = 6): Result<FeaturedReviewsResponseDto>
}
