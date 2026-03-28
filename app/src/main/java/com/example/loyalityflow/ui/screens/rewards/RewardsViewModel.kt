package com.example.loyalityflow.ui.screens.rewards

import androidx.lifecycle.ViewModel
import com.example.loyalityflow.data.model.Reward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RewardsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RewardsUiState())
    val uiState: StateFlow<RewardsUiState> = _uiState.asStateFlow()

    fun getRewardById(id: Int): Reward? {
        return _uiState.value.rewards.firstOrNull { it.id == id }
    }
}
