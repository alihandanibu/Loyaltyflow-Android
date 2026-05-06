package com.example.loyalityflow.presentation.ui.screens.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyalityflow.data.repository.LoyaltyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(
    private val repository: LoyaltyRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<OffersUiState>(OffersUiState.Loading)
    val uiState: StateFlow<OffersUiState> = _uiState.asStateFlow()

    init {
        loadOffers()
    }

    private fun loadOffers() {
        viewModelScope.launch {
            _uiState.value = OffersUiState.Loading
            try {
                repository.getAllOffers().collect { offersList ->
                    _uiState.value = OffersUiState.Success(offersList)
                }
            } catch (e: Exception) {
                _uiState.value = OffersUiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}
