package com.example.loyalityflow.ui.screens.rewards

import com.example.loyalityflow.data.model.Reward
import com.example.loyalityflow.data.model.sampleRewards

data class RewardsUiState(
    val rewards: List<Reward> = sampleRewards,
    // Derived states for Assignment 2
    val affordableRewardsCount: Int = 0,
    val showHighValueRewardsOnly: Boolean = false,
    val searchKeyword: String = ""
)
