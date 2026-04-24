package com.example.loyalityflow.presentation.ui.screens.offers

import com.example.loyalityflow.model.Offer
import com.example.loyalityflow.model.sampleOffers

data class OffersUiState(
    val offers: List<Offer> = sampleOffers
)
