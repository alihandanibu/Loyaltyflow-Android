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

    init {
        updateFilteredRewards()
    }

    fun onCategorySelected(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
        updateFilteredRewards()
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchKeyword = query) }
        updateFilteredRewards()
    }

    private fun updateFilteredRewards() {
        _uiState.update { current ->
            val filteredByCategory = if (current.selectedCategory == "All") {
                sampleRewards
            } else {
                sampleRewards.filter { it.category == current.selectedCategory }
            }

            val filteredBySearch = if (current.searchKeyword.isEmpty()) {
                filteredByCategory
            } else {
                filteredByCategory.filter {
                    it.title.contains(current.searchKeyword, ignoreCase = true) ||
                    it.description.contains(current.searchKeyword, ignoreCase = true)
                }
            }

            current.copy(
                rewards = filteredBySearch,
                affordableRewardsCount = filteredBySearch.count { it.pointsRequired < 100 }
            )
        }
    }

    fun getRewardById(id: Int): Reward? {
        return sampleRewards.firstOrNull { it.id == id }
    }
}
