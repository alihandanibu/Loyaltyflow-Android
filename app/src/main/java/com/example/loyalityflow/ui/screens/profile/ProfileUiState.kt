package com.example.loyalityflow.ui.screens.profile

import com.example.loyalityflow.data.model.ProfileInfo

data class ProfileUiState(
    val profileInfo: ProfileInfo = ProfileInfo(
        fullName = "Ali Handan",
        email = "ali@example.com",
        membershipSince = "March 2026",
        preferredStore = "LoyaltyFlow Demo Store",
        totalScans = 12,
        totalRewardsRedeemed = 3
    )
)
