package com.example.loyalityflow.ui.screens.home

import com.example.loyalityflow.data.model.DashboardStats
import com.example.loyalityflow.data.model.sampleDashboardStats

data class HomeUiState(
    val stats: DashboardStats = sampleDashboardStats,
    // Derived states added for Assignment 2
    val pointsToNextReward: Int = 0,
    val isGoldTier: Boolean = false,
    val canRedeemSomething: Boolean = false
)
