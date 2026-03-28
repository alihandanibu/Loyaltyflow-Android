package com.example.loyalityflow.ui.screens.rewarddetails

import androidx.lifecycle.ViewModel
import com.example.loyalityflow.data.model.Reward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RewardDetailsViewModel : ViewModel() {
    private val sampleRewards = listOf(
        Reward(1, "Free coffee", "Redeem one regular coffee.", 50, "Food"),
        Reward(2, "Protein bar", "Healthy snack for members.", 80, "Snacks"),
        Reward(3, "10% discount", "Discount on the next order.", 120, "Shopping")
    )

    private val _uiState = MutableStateFlow(RewardDetailsUiState())
    val uiState: StateFlow<RewardDetailsUiState> = _uiState.asStateFlow()

    fun loadReward(rewardId: Int) {
        _uiState.value = RewardDetailsUiState(
            reward = sampleRewards.firstOrNull { it.id == rewardId }
        )
    }
}
