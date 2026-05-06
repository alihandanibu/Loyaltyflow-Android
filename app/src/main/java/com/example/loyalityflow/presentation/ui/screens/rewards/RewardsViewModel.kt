package com.example.loyalityflow.presentation.ui.screens.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyalityflow.data.repository.LoyaltyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val repository: LoyaltyRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RewardsUiState())
    val uiState: StateFlow<RewardsUiState> = _uiState.asStateFlow()

    init {
        observeRewards()
    }

    private fun observeRewards() {
        viewModelScope.launch {
            repository.allRewards.collect { rewardsList ->
                _uiState.update { it.copy(rewards = rewardsList) }
            }
        }
    }

    fun onCategorySelected(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
        // U Assignment 4 ovde možeš dodati filtriranje direktno u bazi (Query)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchKeyword = query) }
    }
}
