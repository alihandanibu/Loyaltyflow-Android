package com.example.loyalityflow.presentation.ui.screens.home

import com.example.loyalityflow.model.DashboardStats
import com.example.loyalityflow.model.sampleDashboardStats

data class HomeUiState(
    val stats: DashboardStats = sampleDashboardStats,
    val pointsToNextReward: Int = 0,
    val isGoldTier: Boolean = false,
    val canRedeemSomething: Boolean = false
)
