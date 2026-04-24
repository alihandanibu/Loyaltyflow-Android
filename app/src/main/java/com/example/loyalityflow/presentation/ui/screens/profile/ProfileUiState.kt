package com.example.loyalityflow.presentation.ui.screens.profile

import com.example.loyalityflow.model.ProfileInfo
import com.example.loyalityflow.model.sampleProfileInfo

data class ProfileUiState(
    val profileInfo: ProfileInfo = sampleProfileInfo
)
