package com.example.loyalityflow.presentation.ui.screens.scan

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScanViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()

    fun onReceiptCodeChange(code: String) {
        _uiState.update { it.copy(receiptCode = code, receiptCodeError = null) }
        validate()
    }

    fun onStoreNameChange(name: String) {
        _uiState.update { it.copy(storeName = name, storeNameError = null) }
        validate()
    }

    private fun validate() {
        val state = _uiState.value
        val isCodeValid = state.receiptCode.isNotBlank()
        val isStoreValid = state.storeName.isNotBlank()
        _uiState.update { it.copy(isSubmitEnabled = isCodeValid && isStoreValid) }
    }

    fun submit() {
        _uiState.update { it.copy(successMessage = "Receipt submitted successfully!", receiptCode = "", storeName = "", isSubmitEnabled = false) }
    }
}
