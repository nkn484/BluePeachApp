package com.bluepeach.app.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluepeach.app.data.model.Category
import com.bluepeach.app.data.model.Product
import com.bluepeach.app.data.repository.ProductRepository
import com.bluepeach.app.data.repository.ProductRepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductListUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val products: List<Product> = emptyList(),
    val categories: List<Category> = emptyList()
)

class ProductListViewModel(
    private val repository: ProductRepository = ProductRepositoryProvider.repository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val productsResult = repository.getProducts(limit = 80)
            val categoriesResult = repository.getCategories()
            val error = productsResult.exceptionOrNull()?.message ?: categoriesResult.exceptionOrNull()?.message

            _uiState.value = ProductListUiState(
                isLoading = false,
                errorMessage = error,
                products = productsResult.getOrNull().orEmpty(),
                categories = categoriesResult.getOrNull().orEmpty()
            )
        }
    }
}
