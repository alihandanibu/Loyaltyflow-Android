package com.example.loyalityflow.presentation.ui.screens.home

import com.example.loyalityflow.model.DashboardStats
import com.example.loyalityflow.model.sampleDashboardStats

/**
 * State class for the Home screen.
 * Includes derived states to satisfy Assignment 2 requirements.
 */
data class HomeUiState(
    val stats: DashboardStats = sampleDashboardStats,
    // Derived state: points remaining to reach the next big milestone (e.g., 2000 points)
    val pointsToNextReward: Int = 0,
    // Derived state: checks if the user is in the premium tier
    val isGoldTier: Boolean = false,
    // Derived state: checks if the user has enough points for any basic reward (min 50)
    val canRedeemSomething: Boolean = false
)
