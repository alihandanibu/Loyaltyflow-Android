package com.example.loyalityflow.ui.screens.rewards

import androidx.lifecycle.ViewModel
import com.example.loyalityflow.data.model.Reward
import com.example.loyalityflow.data.model.sampleRewards
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RewardsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RewardsUiState())
    val uiState: StateFlow<RewardsUiState> = _uiState.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchKeyword = query) }
        updateFilteredRewards()
    }

    private fun updateFilteredRewards() {
        val query = _uiState.value.searchKeyword
        val filtered = if (query.isEmpty()) {
            sampleRewards
        } else {
            sampleRewards.filter { 
                it.title.contains(query, ignoreCase = true) || 
                it.description.contains(query, ignoreCase = true) 
            }
        }
        
        _uiState.update { current ->
            current.copy(
                rewards = filtered,
                affordableRewardsCount = filtered.count { it.pointsRequired < 100 }
            )
        }
    }

    fun getRewardById(id: Int): Reward? {
        return sampleRewards.firstOrNull { it.id == id }
    }
}
