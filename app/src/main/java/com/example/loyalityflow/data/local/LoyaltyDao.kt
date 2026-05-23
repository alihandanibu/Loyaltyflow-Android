package com.example.loyalityflow.data.local

import androidx.room.*
import com.example.loyalityflow.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LoyaltyDao {

    // Rewards
    @Query("SELECT * FROM rewards")
    fun getAllRewards(): Flow<List<Reward>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRewards(rewards: List<Reward>)

    // Offers
    @Query("SELECT * FROM offers")
    fun getAllOffers(): Flow<List<Offer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffers(offers: List<Offer>)

    // Profile
    @Query("SELECT * FROM profile_info LIMIT 1")
    fun getProfile(): Flow<ProfileInfo?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileInfo)

    // Scans
    @Query("SELECT * FROM scans ORDER BY date DESC")
    fun getAllScans(): Flow<List<ScanHistory>>

    @Insert
    suspend fun insertScan(scan: ScanHistory)

    @Delete
    suspend fun deleteScan(scan: ScanHistory) // DODATO ZA CRUD

    // Notifications
    @Query("SELECT * FROM user_notifications ORDER BY timestamp DESC")
    fun getNotifications(): Flow<List<UserNotification>>

    @Update
    suspend fun updateNotification(notification: UserNotification)

    @Query("DELETE FROM user_notifications")
    suspend fun deleteAllNotifications() // DODATO ZA CRUD
}
