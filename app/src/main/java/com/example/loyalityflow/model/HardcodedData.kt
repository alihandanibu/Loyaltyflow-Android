package com.example.loyalityflow.model

import com.example.loyalityflow.data.model.DashboardStats
import com.example.loyalityflow.data.model.Offer
import com.example.loyalityflow.data.model.ProfileInfo
import com.example.loyalityflow.data.model.Reward

val sampleDashboardStats = DashboardStats(
    userName = "Ali",
    totalPoints = 1240,
    tier = "Gold",
    nextRewardTitle = "Free protein bar"
)

val sampleRewards = listOf(
    Reward(1, "Free coffee", "Redeem one regular coffee.", 50, "Food"),
    Reward(2, "Protein bar", "Healthy snack for members.", 80, "Snacks"),
    Reward(3, "10% discount", "Discount on the next order.", 120, "Shopping"),
    Reward(4, "Free Sandwich", "Fresh sandwich of your choice.", 150, "Food"),
    Reward(5, "Logo Mug", "Limited edition company mug.", 200, "Merch")
)

val sampleOffers = listOf(
    Offer(1, "Weekend discount", "Get 15% off on your next purchase.", "15% OFF"),
    Offer(2, "Coffee reward booster", "Earn double points on all hot drinks.", "2x POINTS"),
    Offer(3, "Student loyalty day", "Special bonus for student members.", "+80 POINTS")
)

val sampleProfileInfo = ProfileInfo(
    fullName = "Ali Handan",
    email = "ali@example.com",
    membershipSince = "March 2026",
    preferredStore = "LoyaltyFlow Demo Store",
    totalScans = 12,
    totalRewardsRedeemed = 3
)

val sampleCategories = listOf("All", "Food", "Snacks", "Shopping", "Merch")
