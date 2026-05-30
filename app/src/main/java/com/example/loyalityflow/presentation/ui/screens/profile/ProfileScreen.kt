package com.example.loyalityflow.presentation.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loyalityflow.presentation.ui.components.SectionHeader
import com.example.loyalityflow.presentation.ui.screens.profile.components.ProfileHeader
import com.example.loyalityflow.presentation.ui.screens.profile.components.StatItem

@Composable
fun ProfileScreen(
    onNavigateToAuth: () -> Unit,
    onNavigateToNetwork: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is ProfileUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ProfileUiState.Success -> {
            ProfileContent(
                state = state,
                onNavigateToAuth = onNavigateToAuth,
                onNavigateToNetwork = onNavigateToNetwork
            )
        }
        is ProfileUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun ProfileContent(
    state: ProfileUiState.Success,
    onNavigateToAuth: () -> Unit,
    onNavigateToNetwork: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ProfileHeader(profileInfo = state.profileInfo)
        
        SectionHeader(title = "Account Statistics")
        
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            StatItem(label = "Membership Since", value = state.profileInfo.membershipSince)
            StatItem(label = "Total Scans", value = state.profileInfo.totalScans.toString())
            StatItem(label = "Rewards Redeemed", value = state.profileInfo.totalRewardsRedeemed.toString())
            StatItem(label = "Preferred Store", value = state.profileInfo.preferredStore)
        }

        SectionHeader(title = "Settings & Debug")

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = onNavigateToAuth,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Icon(Icons.Default.Lock, contentDescription = null)
                Spacer(Modifier.padding(4.dp))
                Text("Firebase Auth Settings")
            }

            OutlinedButton(
                onClick = onNavigateToNetwork,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null)
                Spacer(Modifier.padding(4.dp))
                Text("REST API Network Test")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}
