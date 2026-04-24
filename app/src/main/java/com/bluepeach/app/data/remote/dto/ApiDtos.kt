package com.bluepeach.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HealthDto(
    val ok: Boolean = false,
    val service: String? = null
)

@Serializable
data class ProductListResponseDto(
    val items: List<ProductSummaryDto> = emptyList(),
    val total: Int = 0,
    val limit: Int = 0,
    val offset: Int = 0
)

@Serializable
data class ProductSummaryDto(
    @SerialName("ma_san_pham") val id: String,
    val sku: String? = null,
    @SerialName("ten_san_pham") val name: String,
    @SerialName("gia_ban") val price: Int,
    @SerialName("gia_goc") val originalPrice: Int? = null,
    @SerialName("phan_tram_giam") val discountPercent: Int? = null,
    @SerialName("so_luong_ton") val stockQuantity: Int = 0,
    @SerialName("primary_image") val primaryImage: String? = null,
    @SerialName("ma_danh_muc") val categoryId: String? = null,
    @SerialName("ngay_tao") val createdAt: String? = null,
    @SerialName("is_bestseller") val isBestSeller: Boolean = false
)

@Serializable
data class ProductDetailDto(
    @SerialName("ma_san_pham") val id: String,
    val sku: String? = null,
    @SerialName("ten_san_pham") val name: String,
    @SerialName("mo_ta_san_pham") val description: String? = null,
    @SerialName("gia_ban") val price: Int,
    @SerialName("gia_goc") val originalPrice: Int? = null,
    @SerialName("phan_tram_giam") val discountPercent: Int? = null,
    @SerialName("so_luong_ton") val stockQuantity: Int = 0,
    @SerialName("primary_image") val primaryImage: String? = null,
    @SerialName("ma_danh_muc") val categoryId: String? = null,
    @SerialName("ngay_tao") val createdAt: String? = null,
    @SerialName("images") val images: List<ProductImageDto> = emptyList()
)

@Serializable
data class ProductImageDto(
    @SerialName("duong_dan_anh") val imageUrl: String,
    @SerialName("la_anh_chinh") val isPrimary: Boolean = false,
    @SerialName("thu_tu") val order: Int = 0
)

@Serializable
data class CategoryListResponseDto(
    val items: List<CategoryDto> = emptyList()
)

@Serializable
data class CategoryDto(
    @SerialName("ma_danh_muc") val id: String,
    @SerialName("ten_danh_muc") val name: String,
    @SerialName("mo_ta") val description: String? = null,
    @SerialName("ngay_tao") val createdAt: String? = null
)

@Serializable
data class HomeBannerResponseDto(
    val item: HomeBannerDto? = null
)

@Serializable
data class HomeBannerDto(
    @SerialName("ma_banner") val id: String? = null,
    @SerialName("tieu_de") val title: String? = null,
    @SerialName("mo_ta_ngan") val shortDescription: String? = null,
    @SerialName("cta_text") val ctaText: String? = null,
    @SerialName("cta_link") val ctaLink: String? = null,
    @SerialName("image_url") val imageUrl: String? = null
)

@Serializable
data class FeaturedReviewsResponseDto(
    val items: List<FeaturedReviewDto> = emptyList(),
    val total: Int = 0
)

@Serializable
data class FeaturedReviewDto(
    @SerialName("ma_danh_gia") val id: String,
    @SerialName("ma_san_pham") val productId: String? = null,
    @SerialName("ten_nguoi_danh_gia") val customerName: String,
    @SerialName("so_sao") val rating: Int,
    @SerialName("noi_dung") val message: String,
    @SerialName("ngay_tao") val createdAt: String? = null,
    @SerialName("products") val product: ReviewProductDto? = null
)

@Serializable
data class ReviewProductDto(
    @SerialName("ma_san_pham") val id: String,
    @SerialName("ten_san_pham") val name: String,
    @SerialName("primary_image") val primaryImage: String? = null
)

@Serializable
data class ProductReviewsResponseDto(
    val summary: ReviewSummaryDto = ReviewSummaryDto(),
    val items: List<ProductReviewDto> = emptyList()
)

@Serializable
data class ReviewSummaryDto(
    @SerialName("average_rating") val averageRating: Float = 0f,
    @SerialName("total_reviews") val totalReviews: Int = 0
)

@Serializable
data class ProductReviewDto(
    @SerialName("ma_danh_gia") val id: String,
    @SerialName("ten_nguoi_danh_gia") val customerName: String,
    @SerialName("so_sao") val rating: Int,
    @SerialName("noi_dung") val message: String,
    @SerialName("ngay_tao") val createdAt: String? = null
)
