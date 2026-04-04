package com.example.loyalityflow.ui.screens.home

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
import com.example.loyalityflow.data.model.sampleOffers
import com.example.loyalityflow.ui.screens.home.components.DashboardCard
import com.example.loyalityflow.ui.screens.home.components.FeaturedOfferCard
import com.example.loyalityflow.ui.screens.home.components.QuickActionButton
import com.example.loyalityflow.ui.screens.home.components.SectionHeader

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
        DashboardCard(stats = uiState.stats)
        
        SectionHeader(title = "Featured for you")
        LazyRow(
            contentPadding = PaddingValues(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleOffers) { offer ->
                FeaturedOfferCard(offer = offer)
            }
        }

        SectionHeader(title = "Quick actions")
        QuickActionButton(text = "Simulate purchase (+50 points)") {
            viewModel.addDemoPoints()
        }
        QuickActionButton(text = "Go to scan form", onClick = onNavigateToScan)
        QuickActionButton(text = "Browse rewards", onClick = onNavigateToRewards)
        Spacer(modifier = Modifier.height(8.dp))
    }
}
