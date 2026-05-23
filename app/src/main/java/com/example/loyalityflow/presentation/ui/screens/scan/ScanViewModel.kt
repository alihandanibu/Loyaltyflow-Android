package com.example.loyalityflow.presentation.ui.screens.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyalityflow.data.model.ScanHistory
import com.example.loyalityflow.data.repository.LoyaltyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val repository: LoyaltyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScanUiState>(ScanUiState.Editing())
    val uiState: StateFlow<ScanUiState> = _uiState.asStateFlow()

    fun onStoreNameChange(name: String) {
        _uiState.update { currentState ->
            if (currentState is ScanUiState.Editing) {
                currentState.copy(storeName = name, isSubmitEnabled = name.isNotBlank())
            } else currentState
        }
    }

    fun submitScan() {
        val currentState = _uiState.value
        if (currentState is ScanUiState.Editing) {
            viewModelScope.launch {
                _uiState.value = ScanUiState.Loading
                try {
                    val newScan = ScanHistory(
                        userEmail = "ali@example.com", // Primer
                        date = Date().toString(),
                        pointsEarned = 50,
                        storeName = currentState.storeName
                    )
                    repository.insertScan(newScan)
                    _uiState.value = ScanUiState.Success("Receipt for ${currentState.storeName} submitted!")
                } catch (e: Exception) {
                    _uiState.value = ScanUiState.Error(e.message ?: "Failed to save scan")
                }
            }
        }
    }
    
    fun resetForm() {
        _uiState.value = ScanUiState.Editing()
    }
}
