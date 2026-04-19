package com.example.loyalityflow.ui.screens.rewards

import com.example.loyalityflow.data.model.Reward
import com.example.loyalityflow.data.model.sampleRewards

data class RewardsUiState(
    val rewards: List<Reward> = sampleRewards,
    val selectedCategory: String = "All",
    val searchKeyword: String = "",
    val affordableRewardsCount: Int = 0
)
