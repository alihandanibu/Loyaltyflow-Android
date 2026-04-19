package com.example.loyalityflow.presentation.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loyalityflow.model.sampleOffers
import com.example.loyalityflow.presentation.ui.components.SectionHeader
import com.example.loyalityflow.presentation.ui.screens.home.components.DashboardCard
import com.example.loyalityflow.presentation.ui.screens.home.components.FeaturedOfferCard
import com.example.loyalityflow.presentation.ui.screens.home.components.QuickActionButton

/**
 * Main dashboard screen.
 * Uses a vertical scroll for the main content and a LazyRow for featured items.
 */
@Composable
fun HomeScreen(
    onNavigateToScan: () -> Unit,
    onNavigateToRewards: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Dashboard section with user stats
        DashboardCard(stats = uiState.stats)
        
        // Requirement: At least two LazyRow elements (This is the first one)
        SectionHeader(title = "Featured for you")
        LazyRow(
            contentPadding = PaddingValues(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleOffers) { offer ->
                FeaturedOfferCard(offer = offer)
            }
        }

        // Quick interaction buttons
        SectionHeader(title = "Quick actions")
        QuickActionButton(text = "Simulate purchase (+50 points)") {
            viewModel.addDemoPoints()
        }
        QuickActionButton(text = "Go to scan form", onClick = onNavigateToScan)
        QuickActionButton(text = "Browse rewards", onClick = onNavigateToRewards)
        
        Spacer(modifier = Modifier.height(8.dp))
    }
}
