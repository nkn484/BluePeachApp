package com.bluepeach.app.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluepeach.app.core.common.SampleStorefrontData
import com.bluepeach.app.data.model.Category
import com.bluepeach.app.data.model.CustomerReview
import com.bluepeach.app.data.model.HomeBanner
import com.bluepeach.app.data.model.Product
import com.bluepeach.app.data.repository.ProductRepository
import com.bluepeach.app.data.repository.ProductRepositoryProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val banner: HomeBanner? = null,
    val categories: List<Category> = emptyList(),
    val newArrivals: List<Product> = emptyList(),
    val bestSellers: List<Product> = emptyList(),
    val featuredReviews: List<CustomerReview> = emptyList()
)

class HomeViewModel(
    private val repository: ProductRepository = ProductRepositoryProvider.repository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val banner = async { repository.getHomeBanner() }
            val categories = async { repository.getCategories() }
            val newArrivals = async { repository.getProducts(sort = "new", limit = 8) }
            val bestSellers = async { repository.getProducts(sort = "best", limit = 8) }
            val reviews = async { repository.getFeaturedReviews(limit = 6) }

            val results = listOf(
                banner.await(),
                categories.await(),
                newArrivals.await(),
                bestSellers.await(),
                reviews.await()
            )
            val firstError = results.firstNotNullOfOrNull { it.exceptionOrNull()?.message }

            _uiState.value = HomeUiState(
                isLoading = false,
                errorMessage = firstError,
                banner = banner.await().getOrNull(),
                categories = categories.await().getOrNull().orEmpty(),
                newArrivals = newArrivals.await().getOrNull().orEmpty(),
                bestSellers = bestSellers.await().getOrNull().orEmpty(),
                featuredReviews = reviews.await().getOrNull().orEmpty()
            )
        }
    }
}

fun HomeUiState.withSampleFallback(): HomeUiState {
    return copy(
        categories = categories.ifEmpty { SampleStorefrontData.categories },
        newArrivals = newArrivals.ifEmpty { SampleStorefrontData.newArrivals },
        bestSellers = bestSellers.ifEmpty { SampleStorefrontData.bestSellers },
        featuredReviews = featuredReviews.ifEmpty { SampleStorefrontData.featuredReviews }
    )
}
