package com.example.loyalityflow.presentation.ui.screens.rewarddetails

import com.example.loyalityflow.data.model.Reward

sealed interface RewardDetailsUiState {
    data object Loading : RewardDetailsUiState
    data class Success(val reward: Reward) : RewardDetailsUiState
    data class Error(val message: String) : RewardDetailsUiState
}
