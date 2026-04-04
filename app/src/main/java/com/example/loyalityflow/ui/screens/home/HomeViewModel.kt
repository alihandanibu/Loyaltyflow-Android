package com.example.loyalityflow.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        updateDerivedStates()
    }

    fun addDemoPoints() {
        _uiState.update { current ->
            current.copy(
                stats = current.stats.copy(totalPoints = current.stats.totalPoints + 50)
            )
        }
        updateDerivedStates()
    }

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
