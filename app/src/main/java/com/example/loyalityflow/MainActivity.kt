package com.example.loyalityflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.loyalityflow.presentation.navigation.LoyaltyFlowApp
import com.example.loyalityflow.presentation.theme.LoyalityFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // LoyaltyFlowTheme se sada nalazi u presentation.theme
            LoyalityFlowTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Glavna aplikacija se sada nalazi u presentation.navigation
                    LoyaltyFlowApp()
                }
            }
        }
    }
}
