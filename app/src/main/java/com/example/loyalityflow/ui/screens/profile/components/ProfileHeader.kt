package com.example.loyalityflow.ui.screens.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.example.loyalityflow.data.model.ProfileInfo

@Composable
fun ProfileHeader(profileInfo: ProfileInfo) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(text = profileInfo.fullName, style = MaterialTheme.typography.headlineSmall)
        Text(text = profileInfo.email, style = MaterialTheme.typography.bodyLarge)
        Text(text = "Member since ${profileInfo.membershipSince}", style = MaterialTheme.typography.bodyMedium)
    }
}
