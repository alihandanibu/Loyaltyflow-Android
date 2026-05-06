package com.example.loyalityflow.presentation.ui.screens.offers

import com.example.loyalityflow.data.model.Offer

sealed interface OffersUiState {
    data object Loading : OffersUiState
    data class Success(val offers: List<Offer>) : OffersUiState
    data class Error(val message: String) : OffersUiState
}
