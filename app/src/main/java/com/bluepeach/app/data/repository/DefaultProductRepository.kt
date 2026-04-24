package com.bluepeach.app.data.repository

import com.bluepeach.app.data.model.Category
import com.bluepeach.app.data.model.CustomerReview
import com.bluepeach.app.data.model.HomeBanner
import com.bluepeach.app.data.model.Product
import com.bluepeach.app.data.remote.BluePeachRemoteDataSource
import com.bluepeach.app.data.remote.RetrofitBluePeachRemoteDataSource

class DefaultProductRepository(
    private val remoteDataSource: BluePeachRemoteDataSource = RetrofitBluePeachRemoteDataSource()
) : ProductRepository {
    override suspend fun getProducts(
        query: String?,
        categoryId: String?,
        sort: String?,
        limit: Int,
        offset: Int
    ): Result<List<Product>> {
        return remoteDataSource.getProducts(
            query = query,
            categoryId = categoryId,
            sort = sort,
            limit = limit,
            offset = offset
        ).map { response ->
            response.items.map { it.toProduct() }
        }
    }

    override suspend fun getProductDetail(productId: String): Result<Product?> {
        val detailResult = remoteDataSource.getProductDetail(productId)
        if (detailResult.isFailure) return Result.failure(detailResult.exceptionOrNull() ?: IllegalStateException())

        val reviews = remoteDataSource.getProductReviews(productId).getOrNull()?.summary
        return Result.success(
            detailResult.getOrNull()?.toProduct(
                reviewRating = reviews?.averageRating ?: 0f,
                reviewCount = reviews?.totalReviews ?: 0
            )
        )
    }

    override suspend fun getCategories(): Result<List<Category>> {
        return remoteDataSource.getCategories().map { response ->
            response.items.map { it.toCategory() }
        }
    }

    override suspend fun getFeaturedReviews(limit: Int): Result<List<CustomerReview>> {
        return remoteDataSource.getFeaturedReviews(limit).map { response ->
            response.items.map { it.toCustomerReview() }
        }
    }

    override suspend fun getHomeBanner(): Result<HomeBanner?> {
        return remoteDataSource.getHomeBanner().map { response ->
            response.item?.toHomeBanner()
        }
    }
}

object ProductRepositoryProvider {
    val repository: ProductRepository by lazy {
        DefaultProductRepository()
    }
}
