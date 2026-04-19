package com.example.loyalityflow.presentation.ui.screens.rewarddetails

import androidx.lifecycle.ViewModel
import com.example.loyalityflow.model.sampleRewards
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RewardDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RewardDetailsUiState())
    val uiState: StateFlow<RewardDetailsUiState> = _uiState.asStateFlow()

    fun loadReward(id: Int) {
        val reward = sampleRewards.firstOrNull { it.id == id }
        _uiState.update { it.copy(reward = reward) }
    }
}
