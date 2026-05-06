package com.example.loyalityflow.presentation.ui.screens.rewarddetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyalityflow.data.repository.LoyaltyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardDetailsViewModel @Inject constructor(
    private val repository: LoyaltyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RewardDetailsUiState>(RewardDetailsUiState.Loading)
    val uiState: StateFlow<RewardDetailsUiState> = _uiState.asStateFlow()

    fun loadReward(rewardId: Int) {
        viewModelScope.launch {
            _uiState.value = RewardDetailsUiState.Loading
            try {
                repository.getAllRewards().collectLatest { rewards ->
                    val reward = rewards.find { it.id == rewardId }
                    if (reward != null) {
                        _uiState.value = RewardDetailsUiState.Success(reward)
                    } else {
                        _uiState.value = RewardDetailsUiState.Error("Reward not found")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = RewardDetailsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
