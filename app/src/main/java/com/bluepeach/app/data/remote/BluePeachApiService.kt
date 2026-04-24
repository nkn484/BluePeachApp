package com.bluepeach.app.data.remote

import com.bluepeach.app.data.remote.dto.CategoryListResponseDto
import com.bluepeach.app.data.remote.dto.FeaturedReviewsResponseDto
import com.bluepeach.app.data.remote.dto.HealthDto
import com.bluepeach.app.data.remote.dto.HomeBannerResponseDto
import com.bluepeach.app.data.remote.dto.ProductDetailDto
import com.bluepeach.app.data.remote.dto.ProductListResponseDto
import com.bluepeach.app.data.remote.dto.ProductReviewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BluePeachApiService {
    @GET("health")
    suspend fun health(): HealthDto

    @GET("products")
    suspend fun products(
        @Query("q") query: String? = null,
        @Query("categoryId") categoryId: String? = null,
        @Query("sort") sort: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("minPrice") minPrice: Int? = null,
        @Query("maxPrice") maxPrice: Int? = null
    ): ProductListResponseDto

    @GET("products/{id}")
    suspend fun productDetail(
        @Path("id") productId: String
    ): ProductDetailDto

    @GET("products/{id}/related")
    suspend fun relatedProducts(
        @Path("id") productId: String,
        @Query("limit") limit: Int = 4
    ): ProductListResponseDto

    @GET("products/{id}/reviews")
    suspend fun productReviews(
        @Path("id") productId: String
    ): ProductReviewsResponseDto

    @GET("categories")
    suspend fun categories(): CategoryListResponseDto

    @GET("banners/home")
    suspend fun homeBanner(): HomeBannerResponseDto

    @GET("reviews/featured")
    suspend fun featuredReviews(
        @Query("limit") limit: Int = 6
    ): FeaturedReviewsResponseDto
}
