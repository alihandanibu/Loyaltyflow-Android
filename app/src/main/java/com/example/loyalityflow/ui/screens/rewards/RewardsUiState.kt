package com.example.loyalityflow.ui.screens.rewards

import com.example.loyalityflow.data.model.Reward

data class RewardsUiState(
    val rewards: List<Reward> = listOf(
        Reward(1, "Free coffee", "Redeem one regular coffee.", 50, "Food"),
        Reward(2, "Protein bar", "Healthy snack for members.", 80, "Snacks"),
        Reward(3, "10% discount", "Discount on the next order.", 120, "Shopping")
    )
)
