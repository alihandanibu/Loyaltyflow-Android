package com.example.loyalityflow.presentation.ui.screens.auth

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val userEmail: String? = null,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
