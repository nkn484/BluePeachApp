package com.bluepeach.app.data.remote

import com.bluepeach.app.data.remote.dto.CategoryListResponseDto
import com.bluepeach.app.data.remote.dto.FeaturedReviewsResponseDto
import com.bluepeach.app.data.remote.dto.HomeBannerResponseDto
import com.bluepeach.app.data.remote.dto.ProductDetailDto
import com.bluepeach.app.data.remote.dto.ProductListResponseDto
import com.bluepeach.app.data.remote.dto.ProductReviewsResponseDto

class RetrofitBluePeachRemoteDataSource(
    private val api: BluePeachApiService = BluePeachApiClient.service
) : BluePeachRemoteDataSource {
    override suspend fun healthCheck(): Result<Unit> = runCatching {
        val health = api.health()
        check(health.ok) { "Backend health check failed" }
    }

    override suspend fun getProducts(
        query: String?,
        categoryId: String?,
        sort: String?,
        limit: Int,
        offset: Int,
        minPrice: Int?,
        maxPrice: Int?
    ): Result<ProductListResponseDto> = runCatching {
        api.products(
            query = query,
            categoryId = categoryId,
            sort = sort,
            limit = limit,
            offset = offset,
            minPrice = minPrice,
            maxPrice = maxPrice
        )
    }

    override suspend fun getProductDetail(productId: String): Result<ProductDetailDto> = runCatching {
        api.productDetail(productId)
    }

    override suspend fun getRelatedProducts(
        productId: String,
        limit: Int
    ): Result<ProductListResponseDto> = runCatching {
        api.relatedProducts(productId = productId, limit = limit)
    }

    override suspend fun getProductReviews(productId: String): Result<ProductReviewsResponseDto> = runCatching {
        api.productReviews(productId)
    }

    override suspend fun getCategories(): Result<CategoryListResponseDto> = runCatching {
        api.categories()
    }

    override suspend fun getHomeBanner(): Result<HomeBannerResponseDto> = runCatching {
        api.homeBanner()
    }

    override suspend fun getFeaturedReviews(limit: Int): Result<FeaturedReviewsResponseDto> = runCatching {
        api.featuredReviews(limit)
    }
}
