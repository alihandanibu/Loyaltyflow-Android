package com.example.loyalityflow.ui.screens.home

import com.example.loyalityflow.data.model.DashboardStats

data class HomeUiState(
    val stats: DashboardStats = DashboardStats(
        userName = "Ali",
        totalPoints = 1240,
        tier = "Gold",
        nextRewardTitle = "Free protein bar"
    )
)
