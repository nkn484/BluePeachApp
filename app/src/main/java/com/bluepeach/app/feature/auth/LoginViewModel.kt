package com.bluepeach.app.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluepeach.app.data.auth.BluePeachAuthRepository
import com.bluepeach.app.data.auth.SupabaseBluePeachAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSignedIn: Boolean = false
)

class LoginViewModel(
    private val authRepository: BluePeachAuthRepository = SupabaseBluePeachAuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        LoginUiState(
            isSignedIn = authRepository.isSignedIn()
        )
    )
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, errorMessage = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, errorMessage = null) }
    }

    fun refreshSession() {
        _uiState.update { it.copy(isSignedIn = authRepository.isSignedIn()) }
    }

    fun signIn(onSuccess: () -> Unit) {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Nhập email và mật khẩu.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = authRepository.signInWithEmailPassword(
                email = state.email.trim(),
                password = state.password
            )
            result.onSuccess {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isSignedIn = true,
                        errorMessage = null
                    )
                }
                onSuccess()
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Đăng nhập thất bại."
                    )
                }
            }
        }
    }

    fun signOut(onSignedOut: () -> Unit = {}) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = authRepository.signOut()
            result.onSuccess {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isSignedIn = false,
                        email = "",
                        password = "",
                        errorMessage = null
                    )
                }
                onSignedOut()
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Đăng xuất thất bại."
                    )
                }
            }
        }
    }
}
