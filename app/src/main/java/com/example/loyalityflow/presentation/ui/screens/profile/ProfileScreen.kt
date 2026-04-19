package com.example.loyalityflow.presentation.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loyalityflow.presentation.ui.components.SectionHeader

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val info = uiState.profileInfo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SectionHeader(title = "Account Details")
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(text = "Name", style = MaterialTheme.typography.labelLarge)
        Text(text = info.fullName, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(text = "Email", style = MaterialTheme.typography.labelLarge)
        Text(text = info.email, style = MaterialTheme.typography.bodyLarge)
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(text = "Membership Since", style = MaterialTheme.typography.labelLarge)
        Text(text = info.membershipSince, style = MaterialTheme.typography.bodyLarge)
    }
}
