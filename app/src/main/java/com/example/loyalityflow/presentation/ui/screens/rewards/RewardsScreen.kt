package com.example.loyalityflow.presentation.ui.screens.rewards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RewardsScreen(
    viewModel: RewardsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Firestore Realtime Rewards",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.addDemoReward() }
        ) {
            Text("Add Firestore Reward")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(visible = uiState.isLoading) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(visible = uiState.successMessage != null) {
            Text(
                text = uiState.successMessage.orEmpty(),
                color = MaterialTheme.colorScheme.primary
            )
        }

        AnimatedVisibility(visible = uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage.orEmpty(),
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(uiState.rewards) { reward ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = reward.title,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Points: ${reward.points}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = "Document ID: ${reward.id}",
                            style = MaterialTheme.typography.bodySmall
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = { viewModel.deleteReward(reward.id) }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
