package com.example.loyalityflow.ui.screens.rewarddetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loyalityflow.ui.screens.rewarddetails.components.DetailRow
import com.example.loyalityflow.ui.screens.rewarddetails.components.RedeemButton

@Composable
fun RewardDetailsScreen(
    rewardId: Int,
    onBackClick: () -> Unit,
    viewModel: RewardDetailsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(rewardId) {
        viewModel.loadReward(rewardId)
    }

    val reward = uiState.reward

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = onBackClick) {
            Text(text = "Back")
        }

        if (reward == null) {
            Text(text = "Reward not found.", style = MaterialTheme.typography.bodyLarge)
        } else {
            Text(text = reward.title, style = MaterialTheme.typography.headlineMedium)
            DetailRow(label = "Points", value = reward.pointsRequired.toString())
            DetailRow(label = "Category", value = reward.category)
            Text(text = reward.description, style = MaterialTheme.typography.bodyLarge)
            RedeemButton(onClick = { })
        }
    }
}
