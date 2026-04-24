package com.bluepeach.app.data.repository

import com.bluepeach.app.core.common.SampleStorefrontData
import com.bluepeach.app.data.model.Category
import com.bluepeach.app.data.model.CustomerReview
import com.bluepeach.app.data.model.HomeBanner
import com.bluepeach.app.data.model.Product
import com.bluepeach.app.data.remote.dto.CategoryDto
import com.bluepeach.app.data.remote.dto.FeaturedReviewDto
import com.bluepeach.app.data.remote.dto.HomeBannerDto
import com.bluepeach.app.data.remote.dto.ProductDetailDto
import com.bluepeach.app.data.remote.dto.ProductSummaryDto

private const val FALLBACK_IMAGE =
    "https://images.unsplash.com/photo-1515562141207-7a88fb7ce338?auto=format&fit=crop&w=900&q=80"

fun ProductSummaryDto.toProduct(): Product {
    return Product(
        id = id,
        sku = sku.orEmpty(),
        name = name,
        categoryId = categoryId.orEmpty(),
        shortDescription = "Trang sức bạc Blue Peach tinh giản, nhẹ nhàng và thanh lịch cho mỗi ngày.",
        detailDescription = "Thiết kế bạc được tuyển chọn theo tinh thần tối giản, nữ tính và dễ phối đồ.",
        priceCents = price,
        originalPriceCents = originalPrice,
        discountPercent = discountPercent,
        stockQuantity = stockQuantity,
        primaryImageUrl = primaryImage.orEmpty(),
        isBestSeller = isBestSeller,
        isNewArrival = true,
        isRing = categoryId?.contains("nhan", ignoreCase = true) == true ||
            categoryId?.contains("ring", ignoreCase = true) == true
    )
}

fun ProductDetailDto.toProduct(reviewRating: Float = 0f, reviewCount: Int = 0): Product {
    val gallery = images
        .sortedWith(compareByDescending<com.bluepeach.app.data.remote.dto.ProductImageDto> { it.isPrimary }.thenBy { it.order })
        .map { it.imageUrl }
        .ifEmpty { listOfNotNull(primaryImage) }

    return Product(
        id = id,
        sku = sku.orEmpty(),
        name = name,
        categoryId = categoryId.orEmpty(),
        shortDescription = description?.lineSequence()?.firstOrNull().orEmpty()
            .ifBlank { "Thiết kế bạc Blue Peach tinh giản, dễ đeo hằng ngày." },
        detailDescription = description.orEmpty()
            .ifBlank { "Thiết kế bạc được tuyển chọn theo tinh thần tối giản, nữ tính và thanh lịch." },
        priceCents = price,
        originalPriceCents = originalPrice,
        discountPercent = discountPercent,
        stockQuantity = stockQuantity,
        primaryImageUrl = primaryImage ?: gallery.firstOrNull().orEmpty(),
        galleryImageUrls = gallery,
        rating = reviewRating,
        reviewCount = reviewCount,
        isBestSeller = false,
        isNewArrival = false,
        isRing = categoryId?.contains("nhan", ignoreCase = true) == true ||
            categoryId?.contains("ring", ignoreCase = true) == true
    )
}

fun CategoryDto.toCategory(): Category {
    val fallback = SampleStorefrontData.categories.firstOrNull { it.id == id }?.imageUrl ?: FALLBACK_IMAGE
    return Category(
        id = id,
        name = name,
        imageUrl = fallback
    )
}

fun FeaturedReviewDto.toCustomerReview(): CustomerReview {
    return CustomerReview(
        id = id,
        productId = productId.orEmpty(),
        productName = product?.name.orEmpty().ifBlank { "Blue Peach" },
        customerName = customerName,
        rating = rating,
        message = message,
        dateLabel = createdAt.orEmpty(),
        productImageUrl = product?.primaryImage.orEmpty()
    )
}

fun HomeBannerDto.toHomeBanner(): HomeBanner {
    return HomeBanner(
        id = id.orEmpty(),
        title = title.orEmpty().ifBlank { SampleStorefrontData.homeHeroTitle },
        description = shortDescription.orEmpty().ifBlank { SampleStorefrontData.homeHeroDescription },
        ctaText = ctaText.orEmpty().ifBlank { "Mua ngay" },
        ctaLink = ctaLink,
        imageUrl = imageUrl.orEmpty().ifBlank { SampleStorefrontData.homeHeroImageUrl }
    )
}
