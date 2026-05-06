package com.example.loyalityflow.presentation.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.loyalityflow.presentation.ui.screens.home.components.DashboardCard
import com.example.loyalityflow.presentation.ui.screens.home.components.FeaturedOfferCard
import com.example.loyalityflow.presentation.ui.screens.home.components.QuickActionButton

/**
 * STATEFUL SCREEN implementation.
 * Connects with the ViewModel and handles state.
 */
@Composable
fun HomeScreen(
    onNavigateToScan: () -> Unit,
    onNavigateToRewards: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is HomeUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is HomeUiState.Success -> {
            HomeContent(
                state = state,
                onAddPoints = { viewModel.addDemoPoints() },
                onNavigateToScan = onNavigateToScan,
                onNavigateToRewards = onNavigateToRewards
            )
        }
        is HomeUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message, color = MaterialTheme.colorScheme.error)
            }
        }
        else -> {}
    }
}

/**
 * STATELESS SCREEN implementation.
 * Purely UI, receives data via parameters.
 */
@Composable
fun HomeContent(
    state: HomeUiState.Success,
    onAddPoints: () -> Unit,
    onNavigateToScan: () -> Unit,
    onNavigateToRewards: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DashboardCard(stats = state.stats)
        
        SectionHeader(title = "Featured for you")
        LazyRow(
            contentPadding = PaddingValues(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.featuredOffers) { offer ->
                FeaturedOfferCard(offer = offer)
            }
        }

        SectionHeader(title = "Quick actions")
        QuickActionButton(text = "Simulate purchase (+50 points)", onClick = onAddPoints)
        QuickActionButton(text = "Go to scan form", onClick = onNavigateToScan)
        QuickActionButton(text = "Browse rewards", onClick = onNavigateToRewards)
        
        Spacer(modifier = Modifier.height(8.dp))
    }
}
