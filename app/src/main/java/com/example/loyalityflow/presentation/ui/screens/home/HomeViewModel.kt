package com.example.loyalityflow.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the Home screen.
 * Manages UI state and business logic for the dashboard.
 */
class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        // Initialize derived states on startup
        updateDerivedStates()
    }

    /**
     * Simulates adding points to the user's account.
     * Demonstrates UI reacting to state changes.
     */
    fun addDemoPoints() {
        _uiState.update { current ->
            current.copy(
                stats = current.stats.copy(totalPoints = current.stats.totalPoints + 50)
            )
        }
        // Always update derived states when the source data changes
        updateDerivedStates()
    }

    /**
     * Calculates derived states based on raw data.
     * (Requirement: At least three derived/computed states).
     */
    private fun updateDerivedStates() {
        _uiState.update { current ->
            val points = current.stats.totalPoints
            current.copy(
                pointsToNextReward = if (points < 2000) 2000 - points else 0,
                isGoldTier = current.stats.tier == "Gold",
                canRedeemSomething = points >= 50
            )
        }
    }
}
