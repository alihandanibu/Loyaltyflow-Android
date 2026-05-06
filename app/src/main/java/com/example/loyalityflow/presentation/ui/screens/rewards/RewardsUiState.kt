package com.example.loyalityflow.presentation.ui.screens.rewards

import com.example.loyalityflow.data.model.Reward

/**
 * Requirement: Each ViewModel should have its own UiState (Init, Success, Loading, Error).
 */
sealed interface RewardsUiState {
    data object Loading : RewardsUiState
    
    data class Success(
        val rewards: List<Reward>,
        val categories: List<String>,
        val selectedCategory: String,
        val searchKeyword: String,
        val affordableRewardsCount: Int
    ) : RewardsUiState
    
    data class Error(val message: String) : RewardsUiState
}
