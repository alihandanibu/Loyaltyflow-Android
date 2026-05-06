package com.example.loyalityflow.presentation.ui.screens.rewards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loyalityflow.presentation.ui.components.SectionHeader
import com.example.loyalityflow.presentation.ui.screens.rewards.components.CategoryChip
import com.example.loyalityflow.presentation.ui.screens.rewards.components.EmptyRewardsState
import com.example.loyalityflow.presentation.ui.screens.rewards.components.RewardCard
import com.example.loyalityflow.presentation.ui.screens.rewards.components.SearchBar

/**
 * STATEFUL Screen - Connected to ViewModel
 */
@Composable
fun RewardsScreen(
    onRewardClick: (Int, String) -> Unit,
    viewModel: RewardsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is RewardsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is RewardsUiState.Success -> {
            RewardsContent(
                state = state,
                onRewardClick = onRewardClick,
                onSearchChange = { viewModel.onSearchQueryChanged(it) },
                onCategorySelect = { viewModel.onCategorySelected(it) }
            )
        }
        is RewardsUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

/**
 * STATELESS Screen - Purely for UI
 */
@Composable
fun RewardsContent(
    state: RewardsUiState.Success,
    onRewardClick: (Int, String) -> Unit,
    onSearchChange: (String) -> Unit,
    onCategorySelect: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SectionHeader(title = "Reward catalog")
        
        SearchBar(
            query = state.searchKeyword,
            onQueryChange = onSearchChange
        )

        LazyRow(
            contentPadding = PaddingValues(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(state.categories) { category ->
                CategoryChip(
                    category = category,
                    isSelected = state.selectedCategory == category,
                    onClick = { onCategorySelect(category) }
                )
            }
        }

        if (state.affordableRewardsCount > 0) {
            Text(
                text = "You have ${state.affordableRewardsCount} affordable rewards!",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (state.rewards.isEmpty()) {
            EmptyRewardsState()
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.rewards) { reward ->
                    RewardCard(reward = reward, onClick = { onRewardClick(reward.id, reward.title) })
                }
            }
        }
    }
}
