package com.example.loyalityflow.presentation.ui.screens.scan

data class ScanUiState(
    val receiptCode: String = "",
    val storeName: String = "",
    val receiptCodeError: String? = null,
    val storeNameError: String? = null,
    val successMessage: String? = null,
    val isSubmitEnabled: Boolean = false
)
