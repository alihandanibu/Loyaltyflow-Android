package com.example.loyalityflow.data.local.dao

import androidx.room.*
import com.example.loyalityflow.data.local.entity.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for the Loyalty application.
 * Defines the database operations (CRUD).
 */
@Dao
interface LoyaltyDao {

    // --- Reward Operations ---
    @Query("SELECT * FROM rewards")
    fun getAllRewards(): Flow<List<RewardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRewards(rewards: List<RewardEntity>)

    // --- Offer Operations ---
    @Query("SELECT * FROM offers")
    fun getAllOffers(): Flow<List<OfferEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffers(offers: List<OfferEntity>)

    // --- Profile Operations ---
    @Query("SELECT * FROM profile_info LIMIT 1")
    fun getProfile(): Flow<ProfileInfoEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileInfoEntity)

    // --- Scan History Operations ---
    @Query("SELECT * FROM scans ORDER BY date DESC")
    fun getAllScans(): Flow<List<ScanHistoryEntity>>

    @Insert
    suspend fun insertScan(scan: ScanHistoryEntity)

    @Delete
    suspend fun deleteScan(scan: ScanHistoryEntity)

    // --- Notification Operations ---
    @Query("SELECT * FROM user_notifications ORDER BY timestamp DESC")
    fun getNotifications(): Flow<List<UserNotificationEntity>>

    @Update
    suspend fun updateNotification(notification: UserNotificationEntity)

    @Query("DELETE FROM user_notifications")
    suspend fun deleteAllNotifications()
}
