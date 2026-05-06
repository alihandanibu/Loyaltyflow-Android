package com.example.loyalityflow.data.repository

import com.example.loyalityflow.data.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface defining data operations.
 * Separation of concerns: UI doesn't know about the data source (Room/API).
 */
interface LoyaltyRepository {
    fun getAllRewards(): Flow<List<Reward>>
    suspend fun insertRewards(rewards: List<Reward>)
    
    fun getAllOffers(): Flow<List<Offer>>
    suspend fun insertOffers(offers: List<Offer>)
    
    fun getProfile(): Flow<ProfileInfo?>
    suspend fun insertProfile(profile: ProfileInfo)
    
    fun getAllScans(): Flow<List<ScanHistory>>
    suspend fun insertScan(scan: ScanHistory)
    suspend fun deleteScan(scan: ScanHistory)
    
    fun getNotifications(): Flow<List<UserNotification>>
    suspend fun updateNotification(notification: UserNotification)
    suspend fun deleteAllNotifications()
}
