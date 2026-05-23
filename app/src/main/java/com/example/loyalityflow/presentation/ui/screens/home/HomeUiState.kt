package com.example.loyalityflow.presentation.ui.screens.home

import com.example.loyalityflow.data.model.DashboardStats
import com.example.loyalityflow.data.model.Offer

/**
 * Requirement: Each ViewModel should have its own UiState (Init, Success, Loading, Error).
 * Using a sealed interface allows us to represent these states clearly.
 */
sealed interface HomeUiState {
    data object Init : HomeUiState
    data object Loading : HomeUiState
    
    data class Success(
        val stats: DashboardStats,
        val featuredOffers: List<Offer>,
        val pointsToNextReward: Int,
        val isGoldTier: Boolean,
        val canRedeemSomething: Boolean
    ) : HomeUiState
    
    data class Error(val message: String) : HomeUiState
}
