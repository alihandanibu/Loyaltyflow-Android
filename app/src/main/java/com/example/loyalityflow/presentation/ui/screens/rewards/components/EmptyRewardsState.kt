package com.example.loyalityflow.presentation.ui.screens.rewards.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Requirement: Empty state handling (e.g., “No items available”).
 */
@Composable
fun EmptyRewardsState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No rewards found matching your criteria.")
    }
}
