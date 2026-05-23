package com.example.loyalityflow.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "offers")
data class OfferEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val discountLabel: String,
    val isActive: Boolean = true
)

@Entity(tableName = "rewards")
data class RewardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val pointsRequired: Int,
    val category: String
)

@Entity(tableName = "profile_info")
data class ProfileInfoEntity(
    @PrimaryKey val email: String,
    val fullName: String,
    val membershipSince: String,
    val preferredStore: String,
    val totalScans: Int,
    val totalRewardsRedeemed: Int
)

/**
 * ScanHistory with a One-to-Many relationship with ProfileInfo.
 */
@Entity(
    tableName = "scans",
    foreignKeys = [
        ForeignKey(
            entity = ProfileInfoEntity::class,
            parentColumns = ["email"],
            childColumns = ["userEmail"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userEmail"])]
)
data class ScanHistoryEntity(
    @PrimaryKey(autoGenerate = true) val scanId: Int = 0,
    val userEmail: String,
    val date: String,
    val pointsEarned: Int,
    val storeName: String
)

@Entity(tableName = "user_notifications")
data class UserNotificationEntity(
    @PrimaryKey(autoGenerate = true) val notificationId: Int = 0,
    val title: String,
    val message: String,
    val isRead: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Data class for Dashboard UI stats (not an entity).
 */
data class DashboardStats(
    val userName: String,
    val totalPoints: Int,
    val tier: String,
    val nextRewardTitle: String
)
