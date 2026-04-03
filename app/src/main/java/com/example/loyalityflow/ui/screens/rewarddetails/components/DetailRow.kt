package com.example.loyalityflow.ui.screens.rewarddetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailRow(label: String, value: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "$label: ", style = MaterialTheme.typography.titleSmall)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}
