package com.bluepeach.app.data.repository

import com.bluepeach.app.data.model.Product

/**
 * Repository seam for Step 2 API/Supabase wiring.
 */
interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductDetail(productId: String): Product?
}
