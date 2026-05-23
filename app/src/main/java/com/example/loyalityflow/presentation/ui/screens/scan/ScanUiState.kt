package com.example.loyalityflow.presentation.ui.screens.scan

sealed interface ScanUiState {
    data class Editing(
        val receiptCode: String = "",
        val storeName: String = "",
        val receiptCodeError: String? = null,
        val storeNameError: String? = null,
        val isSubmitEnabled: Boolean = false
    ) : ScanUiState

    data object Loading : ScanUiState
    data class Success(val message: String) : ScanUiState
    data class Error(val message: String) : ScanUiState
}
