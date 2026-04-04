package com.example.loyalityflow.ui.screens.rewards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loyalityflow.ui.screens.home.components.SectionHeader
import com.example.loyalityflow.ui.screens.rewards.components.EmptyRewardsState
import com.example.loyalityflow.ui.screens.rewards.components.RewardCard
import com.example.loyalityflow.ui.screens.rewards.components.SearchBar

@Composable
fun RewardsScreen(
    onRewardClick: (Int) -> Unit,
    viewModel: RewardsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SectionHeader(title = "Reward catalog")
        
        // Search functionality (Requirement: Bonus behavior)
        SearchBar(
            query = uiState.searchKeyword,
            onQueryChange = { viewModel.onSearchQueryChanged(it) }
        )

        // Derived state display example
        if (uiState.affordableRewardsCount > 0) {
            Text(
                text = "You have ${uiState.affordableRewardsCount} affordable rewards!",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (uiState.rewards.isEmpty()) {
            EmptyRewardsState()
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.rewards) { reward ->
                    RewardCard(reward = reward, onClick = { onRewardClick(reward.id) })
                }
            }
        }
    }
}
