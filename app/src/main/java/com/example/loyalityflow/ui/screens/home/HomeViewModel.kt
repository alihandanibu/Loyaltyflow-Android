package com.example.loyalityflow.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun addDemoPoints() {
        val current = _uiState.value
        _uiState.value = current.copy(
            stats = current.stats.copy(totalPoints = current.stats.totalPoints + 50)
        )
    }
}
