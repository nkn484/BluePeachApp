package com.bluepeach.app.feature.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluepeach.app.core.navigation.AppRoute
import com.bluepeach.app.data.model.Product
import com.bluepeach.app.data.repository.ProductRepository
import com.bluepeach.app.data.repository.ProductRepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductDetailUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val product: Product? = null
)

class ProductDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: ProductRepository = ProductRepositoryProvider.repository
) : ViewModel() {
    private val productId: String = checkNotNull(savedStateHandle[AppRoute.PRODUCT_ID_ARG])

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = repository.getProductDetail(productId)
            _uiState.value = ProductDetailUiState(
                isLoading = false,
                errorMessage = result.exceptionOrNull()?.message,
                product = result.getOrNull()
            )
        }
    }
}
