package com.example.loyalityflow.ui.screens.rewarddetails.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun RedeemButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Redeem")
    }
}
