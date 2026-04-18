package com.bluepeach.app.data.model

data class Product(
    val id: String,
    val sku: String,
    val name: String,
    val categoryId: String,
    val shortDescription: String,
    val detailDescription: String,
    val priceCents: Int,
    val originalPriceCents: Int? = null,
    val discountPercent: Int? = null,
    val stockQuantity: Int,
    val primaryImageUrl: String,
    val galleryImageUrls: List<String> = emptyList(),
    val rating: Float = 0f,
    val reviewCount: Int = 0,
    val isBestSeller: Boolean = false,
    val isNewArrival: Boolean = false,
    val isRing: Boolean = false
)
