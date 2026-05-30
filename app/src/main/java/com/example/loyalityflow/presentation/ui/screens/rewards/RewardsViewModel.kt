package com.example.loyalityflow.presentation.ui.screens.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyalityflow.data.firebase.firestore.RewardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val rewardsRepository: RewardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RewardsUiState(isLoading = true))
    val uiState: StateFlow<RewardsUiState> = _uiState.asStateFlow()

    init {
        observeRewards()
    }

    private fun observeRewards() {
        viewModelScope.launch {
            rewardsRepository.observeRewards().collect { result ->
                result
                    .onSuccess { rewards ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            rewards = rewards,
                            errorMessage = null
                        )
                    }
                    .onFailure { exception ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = exception.message ?: "Failed to observe rewards"
                        )
                    }
            }
        }
    }

    fun addDemoReward() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                successMessage = null,
                errorMessage = null
            )

            val result = rewardsRepository.addDemoReward()

            result
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        successMessage = "Reward added. Firestore realtime listener will update the list."
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = exception.message ?: "Failed to add reward"
                    )
                }
        }
    }

    fun deleteReward(id: String) {
        viewModelScope.launch {
            val result = rewardsRepository.deleteReward(id)

            result
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        successMessage = "Reward deleted"
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = exception.message ?: "Failed to delete reward"
                    )
                }
        }
    }
}
