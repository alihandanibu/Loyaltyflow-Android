package com.example.loyalityflow.ui.screens.offers

import com.example.loyalityflow.data.model.Offer

data class OffersUiState(
    val offers: List<Offer> = listOf(
        Offer(1, "Weekend discount", "Get 15% off on your next purchase.", "15% OFF"),
        Offer(2, "Coffee reward booster", "Earn double points on all hot drinks.", "2x POINTS"),
        Offer(3, "Student loyalty day", "Special bonus for student members.", "+80 POINTS")
    )
)
