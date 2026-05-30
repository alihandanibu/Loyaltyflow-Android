package com.example.loyalityflow.presentation.ui.screens.network

import com.example.loyalityflow.data.remote.dto.LoyaltyCardDto

data class LoyaltyCardsUiState(
    val isLoading: Boolean = false,
    val cards: List<LoyaltyCardDto> = emptyList(),
    val successMessage: String? = null,
    val errorMessage: String? = null
)
