package com.example.loyalityflow.ui.screens.scan

data class ScanUiState(
    val receiptCode: String = "",
    val storeName: String = "",
    val receiptCodeError: String? = null,
    val storeNameError: String? = null,
    val isSubmitEnabled: Boolean = false,
    val successMessage: String? = null
)
