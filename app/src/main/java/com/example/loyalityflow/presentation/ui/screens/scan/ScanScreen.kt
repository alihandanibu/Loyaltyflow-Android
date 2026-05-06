package com.example.loyalityflow.presentation.ui.screens.scan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loyalityflow.presentation.ui.components.SectionHeader
import com.example.loyalityflow.presentation.ui.screens.scan.components.ScanInputField

/**
 * STATEFUL Screen - Connected to ViewModel
 */
@Composable
fun ScanScreen(viewModel: ScanViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is ScanUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ScanUiState.Editing -> {
            ScanContent(
                state = state,
                onStoreNameChange = { viewModel.onStoreNameChange(it) },
                onSubmit = { viewModel.submitScan() }
            )
        }
        is ScanUiState.Success -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = state.message, style = MaterialTheme.typography.headlineSmall)
                    Button(onClick = { viewModel.resetForm() }) {
                        Text("Scan another")
                    }
                }
            }
        }
        is ScanUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

/**
 * STATELESS Screen - Pure UI
 */
@Composable
fun ScanContent(
    state: ScanUiState.Editing,
    onStoreNameChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader(title = "Scan Receipt")
        
        ScanInputField(
            value = state.storeName,
            onValueChange = onStoreNameChange,
            label = "Store Name",
            placeholder = "Enter store name (e.g. Walmart)"
        )

        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxSize(),
            enabled = state.isSubmitEnabled
        ) {
            Text("Submit Receipt")
        }
    }
}
