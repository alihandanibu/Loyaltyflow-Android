package com.example.loyalityflow.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loyalityflow.ui.screens.home.components.SectionHeader
import com.example.loyalityflow.ui.screens.profile.components.ProfileHeader
import com.example.loyalityflow.ui.screens.profile.components.StatItem

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val profile = uiState.profileInfo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SectionHeader(title = "Profile")
        ProfileHeader(profileInfo = profile)
        StatItem(label = "Preferred store", value = profile.preferredStore)
        StatItem(label = "Total scans", value = profile.totalScans.toString())
        StatItem(label = "Rewards redeemed", value = profile.totalRewardsRedeemed.toString())
    }
}
