package com.example.loyalityflow.presentation.ui.screens.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyalityflow.data.remote.dto.LoyaltyCardDto
import com.example.loyalityflow.data.remote.repository.LoyaltyCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoyaltyCardsViewModel @Inject constructor(
    private val repository: LoyaltyCardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoyaltyCardsUiState())
    val uiState: StateFlow<LoyaltyCardsUiState> = _uiState.asStateFlow()

    init {
        loadLoyaltyCards()
    }

    fun loadLoyaltyCards() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                successMessage = null,
                errorMessage = null
            )

            val result = repository.getLoyaltyCards()

            result
                .onSuccess { cards ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        cards = cards,
                        successMessage = "Cards loaded from REST API",
                        errorMessage = null
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to load cards"
                    )
                }
        }
    }

    fun createDemoCard() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                successMessage = null,
                errorMessage = null
            )

            val demoCard = LoyaltyCardDto(
                userId = 1,
                storeName = "Loyaltyflow Demo Store",
                description = "Created through Retrofit POST request"
            )

            val result = repository.createLoyaltyCard(demoCard)

            result
                .onSuccess { createdCard ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "POST successful. Created ID: ${createdCard.id}",
                        errorMessage = null
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to create card"
                    )
                }
        }
    }

    fun updateFirstCard() {
        viewModelScope.launch {
            val firstCard = _uiState.value.cards.firstOrNull()

            if (firstCard == null) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "No card available to update"
                )
                return@launch
            }

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                successMessage = null,
                errorMessage = null
            )

            val updatedCard = firstCard.copy(
                storeName = "Updated Loyaltyflow Store",
                description = "Updated through Retrofit PUT request"
            )

            val result = repository.updateLoyaltyCard(firstCard.id, updatedCard)

            result
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "PUT successful for ID: ${firstCard.id}",
                        errorMessage = null
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to update card"
                    )
                }
        }
    }

    fun deleteFirstCard() {
        viewModelScope.launch {
            val firstCard = _uiState.value.cards.firstOrNull()

            if (firstCard == null) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "No card available to delete"
                )
                return@launch
            }

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                successMessage = null,
                errorMessage = null
            )

            val result = repository.deleteLoyaltyCard(firstCard.id)

            result
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "DELETE successful for ID: ${firstCard.id}",
                        errorMessage = null
                    )
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to delete card"
                    )
                }
        }
    }
}
