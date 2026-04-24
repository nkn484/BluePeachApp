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

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)

class RegisterViewModel(
    private val authRepository: BluePeachAuthRepository = SupabaseBluePeachAuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, errorMessage = null, successMessage = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, errorMessage = null, successMessage = null) }
    }

    fun onConfirmPasswordChange(value: String) {
        _uiState.update { it.copy(confirmPassword = value, errorMessage = null, successMessage = null) }
    }

    fun signUp(onSuccess: () -> Unit) {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank() || state.confirmPassword.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Nhập đầy đủ email và mật khẩu.") }
            return
        }
        if (state.password != state.confirmPassword) {
            _uiState.update { it.copy(errorMessage = "Mật khẩu xác nhận không khớp.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
            val result = authRepository.signUpWithEmailPassword(
                email = state.email.trim(),
                password = state.password
            )
            result.onSuccess {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        successMessage = "Đã tạo tài khoản. Nếu project bật xác nhận email, hãy kiểm tra hộp thư.",
                        errorMessage = null
                    )
                }
                onSuccess()
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Đăng ký thất bại."
                    )
                }
            }
        }
    }
}
