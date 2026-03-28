package com.example.loyalityflow.data.model

data class DashboardStats(
    val userName: String,
    val totalPoints: Int,
    val tier: String,
    val nextRewardTitle: String
)

data class Offer(
    val id: Int,
    val title: String,
    val description: String,
    val discountLabel: String,
    val isActive: Boolean = true
)

data class Reward(
    val id: Int,
    val title: String,
    val description: String,
    val pointsRequired: Int,
    val category: String
)

data class ProfileInfo(
    val fullName: String,
    val email: String,
    val membershipSince: String,
    val preferredStore: String,
    val totalScans: Int,
    val totalRewardsRedeemed: Int
)
