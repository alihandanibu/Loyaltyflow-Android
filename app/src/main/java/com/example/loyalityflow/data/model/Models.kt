package com.example.loyalityflow.data.model

data class DashboardStats(
    val userName: String,
    val totalPoints: Int,
    val tier: String,
    val nextRewardTitle: String
)

data class Offer(
    val id: Int = 0,
    val title: String,
    val description: String,
    val discountLabel: String,
    val isActive: Boolean = true
)

data class Reward(
    val id: Int = 0,
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

data class ScanHistory(
    val scanId: Int = 0,
    val userEmail: String,
    val date: String,
    val pointsEarned: Int,
    val storeName: String
)

data class UserNotification(
    val notificationId: Int = 0,
    val title: String,
    val message: String,
    val isRead: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
