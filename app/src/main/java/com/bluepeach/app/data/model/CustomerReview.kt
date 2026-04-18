package com.bluepeach.app.data.model

data class CustomerReview(
    val id: String,
    val productId: String,
    val productName: String,
    val customerName: String,
    val rating: Int,
    val message: String,
    val dateLabel: String,
    val productImageUrl: String
)
