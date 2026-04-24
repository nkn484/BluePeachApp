package com.bluepeach.app.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.core.ui.components.BluePeachPrimaryButton
import com.bluepeach.app.core.ui.components.BluePeachSectionHeader
import com.bluepeach.app.core.ui.components.BluePeachTopBar

@Composable
fun RegisterScreen(
    onBack: (() -> Unit)? = null,
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { BluePeachTopBar(title = "Đăng ký", onBack = onBack) },
        containerColor = BluePeachColors.surfacePlain
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(BluePeachColors.surfacePlain)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BluePeachSectionHeader(
                label = "Supabase Auth",
                title = "Tạo tài khoản mới",
                description = "Tài khoản mới sẽ dùng chung backend Supabase với web.",
                centered = false
            )

            Surface(
                shape = MaterialTheme.shapes.large,
                color = BluePeachColors.surfaceCard,
                border = androidx.compose.foundation.BorderStroke(1.dp, BluePeachColors.borderSoft),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.email,
                        onValueChange = viewModel::onEmailChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Email") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            keyboardType = KeyboardType.Email,
                            autoCorrect = false
                        )
                    )

                    OutlinedTextField(
                        value = uiState.password,
                        onValueChange = viewModel::onPasswordChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Mật khẩu") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            keyboardType = KeyboardType.Password
                        )
                    )

                    OutlinedTextField(
                        value = uiState.confirmPassword,
                        onValueChange = viewModel::onConfirmPasswordChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Xác nhận mật khẩu") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            keyboardType = KeyboardType.Password
                        )
                    )

                    if (!uiState.errorMessage.isNullOrBlank()) {
                        Text(
                            text = uiState.errorMessage.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    if (!uiState.successMessage.isNullOrBlank()) {
                        Text(
                            text = uiState.successMessage.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = BluePeachColors.textSecondary
                        )
                    }

                    BluePeachPrimaryButton(
                        text = if (uiState.isLoading) "Đang tạo tài khoản..." else "Đăng ký",
                        onClick = { viewModel.signUp(onRegisterSuccess) },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !uiState.isLoading
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
