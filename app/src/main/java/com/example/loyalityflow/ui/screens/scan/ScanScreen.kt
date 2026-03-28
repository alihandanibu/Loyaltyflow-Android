package com.example.loyalityflow.ui.screens.scan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loyalityflow.ui.screens.home.components.SectionHeader
import com.example.loyalityflow.ui.screens.scan.components.ScanInputField
import com.example.loyalityflow.ui.screens.scan.components.StatusMessage

@Composable
fun ScanScreen(viewModel: ScanViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SectionHeader(title = "Submit receipt")
        ScanInputField(
            value = uiState.receiptCode,
            onValueChange = viewModel::onReceiptCodeChange,
            label = "Receipt code",
            isError = uiState.receiptCodeError != null,
            supportingText = uiState.receiptCodeError
        )
        ScanInputField(
            value = uiState.storeName,
            onValueChange = viewModel::onStoreNameChange,
            label = "Store name",
            isError = uiState.storeNameError != null,
            supportingText = uiState.storeNameError
        )
        Button(
            onClick = viewModel::submit,
            enabled = uiState.isSubmitEnabled
        ) {
            Text(text = "Submit")
        }
            uiState.successMessage?.let { message ->
            Text(text = message)
        }
    }
}
