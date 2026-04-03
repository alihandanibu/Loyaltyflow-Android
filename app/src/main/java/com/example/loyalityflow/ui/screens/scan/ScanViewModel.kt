package com.example.loyalityflow.ui.screens.scan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScanViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()

    fun onReceiptCodeChange(value: String) {
        _uiState.value = validate(_uiState.value.copy(receiptCode = value, successMessage = null))
    }

    fun onStoreNameChange(value: String) {
        _uiState.value = validate(_uiState.value.copy(storeName = value, successMessage = null))
    }

    fun submit() {
        val state = validate(_uiState.value)
        _uiState.value = if (state.isSubmitEnabled) {
            state.copy(successMessage = "Receipt accepted. Points will be added.")
        } else {
            state
        }
    }

    private fun validate(state: ScanUiState): ScanUiState {
        val receiptCodeError = when {
            state.receiptCode.isBlank() -> "Receipt code is required"
            state.receiptCode.length < 6 -> "Receipt code must have at least 6 characters"
            else -> null
        }

        val storeNameError = when {
            state.storeName.isBlank() -> "Store name is required"
            state.storeName.length < 2 -> "Store name is too short"
            else -> null
        }

        return state.copy(
            receiptCodeError = receiptCodeError,
            storeNameError = storeNameError,
            isSubmitEnabled = receiptCodeError == null && storeNameError == null
        )
    }
}
