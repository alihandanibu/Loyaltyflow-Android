package com.example.loyalityflow.presentation.ui.screens.rewards

import com.example.loyalityflow.data.firebase.firestore.RewardDto

data class RewardsUiState(
    val isLoading: Boolean = false,
    val rewards: List<RewardDto> = emptyList(),
    val successMessage: String? = null,
    val errorMessage: String? = null
)
