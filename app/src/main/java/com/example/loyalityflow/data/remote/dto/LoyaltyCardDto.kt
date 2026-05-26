package com.example.loyalityflow.data.remote.dto

data class LoyaltyCardDto (
    val id: Int,
    val storeName: String,
    val customerName: String,
    val points: Int,
    val isActive: Boolean
)

