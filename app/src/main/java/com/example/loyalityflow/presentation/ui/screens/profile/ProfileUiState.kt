package com.example.loyalityflow.presentation.ui.screens.profile

import com.example.loyalityflow.data.model.ProfileInfo

sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(val profileInfo: ProfileInfo) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}
