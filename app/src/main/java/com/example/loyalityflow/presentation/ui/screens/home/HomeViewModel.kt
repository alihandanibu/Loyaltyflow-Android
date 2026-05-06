package com.example.loyalityflow.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyalityflow.data.model.DashboardStats
import com.example.loyalityflow.data.repository.LoyaltyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LoyaltyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                // Combine multiple flows from repository to create a single UI state
                combine(
                    repository.getAllOffers(),
                    repository.getProfile()
                ) { offers, profile ->
                    if (profile == null) {
                        HomeUiState.Error("Profile not found")
                    } else {
                        val stats = DashboardStats(
                            userName = profile.fullName,
                            totalPoints = profile.totalScans * 10, // Example calculation
                            tier = "Gold",
                            nextRewardTitle = "Free Coffee"
                        )
                        
                        // Calculate derived states as required by assignment
                        val points = stats.totalPoints
                        HomeUiState.Success(
                            stats = stats,
                            featuredOffers = offers,
                            pointsToNextReward = if (points < 2000) 2000 - points else 0,
                            isGoldTier = stats.tier == "Gold",
                            canRedeemSomething = points >= 50
                        )
                    }
                }.collect { state ->
                    _uiState.value = state
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addDemoPoints() {
        // Points update simulation
        viewModelScope.launch {
            // In a real CRUD scenario, update the database here
        }
    }
}
