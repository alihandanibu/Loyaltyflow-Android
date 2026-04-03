package com.example.loyalityflow.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.loyalityflow.data.model.DashboardStats

@Composable
fun DashboardCard(stats: DashboardStats) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Welcome, ${stats.userName}", style = MaterialTheme.typography.headlineSmall)
            Text(text = "Points: ${stats.totalPoints}", style = MaterialTheme.typography.titleLarge)
            Text(text = "Tier: ${stats.tier}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Next target: ${stats.nextRewardTitle}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
