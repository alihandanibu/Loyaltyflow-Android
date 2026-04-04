package com.example.loyalityflow.ui.screens.profile

import com.example.loyalityflow.data.model.ProfileInfo
import com.example.loyalityflow.data.model.sampleProfileInfo

data class ProfileUiState(
    val profileInfo: ProfileInfo = sampleProfileInfo
)
