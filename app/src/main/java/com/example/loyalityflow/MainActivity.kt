package com.example.loyalityflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.loyalityflow.ui.navigation.LoyaltyFlowApp
import com.example.loyalityflow.ui.theme.LoyalityFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoyalityFlowTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LoyaltyFlowApp()
                }
            }
        }
    }
}
