package com.example.loyalityflow.ui.screens.scan.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StatusMessage(message: String) {
    Text(text = message, style = MaterialTheme.typography.bodyMedium)
}
