package com.example.loyalityflow.presentation.ui.screens.rewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyalityflow.data.repository.LoyaltyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val repository: LoyaltyRepository
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow("All")
    private val _searchKeyword = MutableStateFlow("")

    private val _uiState = MutableStateFlow<RewardsUiState>(RewardsUiState.Loading)
    val uiState: StateFlow<RewardsUiState> = _uiState.asStateFlow()

    init {
        observeRewards()
    }

    private fun observeRewards() {
        viewModelScope.launch {
            try {
                // filterNotNull() će zaustaviti tok dok se profil ne pojavi u bazi (koju repository popunjava pri startu)
                repository.getProfile()
                    .filterNotNull()
                    .flatMapLatest { profile ->
                        combine(
                            repository.getAllRewards(),
                            _selectedCategory,
                            _searchKeyword
                        ) { rewards, selectedCategory, searchKeyword ->
                            val points = profile.totalScans * 10
                            val affordableCount = rewards.count { it.pointsRequired <= points }
                            val categories = listOf("All") + rewards.map { it.category }.distinct().sorted()

                            RewardsUiState.Success(
                                rewards = rewards,
                                categories = categories,
                                selectedCategory = selectedCategory,
                                searchKeyword = searchKeyword,
                                affordableRewardsCount = affordableCount
                            )
                        }
                    }.collect { state ->
                        _uiState.value = state
                    }
            } catch (e: Exception) {
                _uiState.value = RewardsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }

    fun onSearchQueryChanged(query: String) {
        _searchKeyword.value = query
    }
}
