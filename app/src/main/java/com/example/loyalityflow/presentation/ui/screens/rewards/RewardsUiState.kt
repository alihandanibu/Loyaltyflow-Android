package com.example.loyalityflow.presentation.ui.screens.rewards

import com.example.loyalityflow.model.Reward
import com.example.loyalityflow.model.sampleRewards

data class RewardsUiState(
    val rewards: List<Reward> = sampleRewards,
    val selectedCategory: String = "All",
    val searchKeyword: String = "",
    val affordableRewardsCount: Int = 0
)
