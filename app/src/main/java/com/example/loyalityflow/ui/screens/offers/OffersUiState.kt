package com.example.loyalityflow.ui.screens.offers

import com.example.loyalityflow.data.model.Offer
import com.example.loyalityflow.data.model.sampleOffers

data class OffersUiState(
    val offers: List<Offer> = sampleOffers
)
