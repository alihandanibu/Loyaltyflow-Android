package com.example.loyalityflow.data.repository

import com.example.loyalityflow.data.local.dao.LoyaltyDao
import com.example.loyalityflow.data.model.*
import com.example.loyalityflow.data.repository.mappers.toDomain
import com.example.loyalityflow.data.repository.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of LoyaltyRepository using Room DAO.
 * Handles data mapping between Entity and Domain models.
 */
class LoyaltyRepositoryImpl @Inject constructor(
    private val loyaltyDao: LoyaltyDao
) : LoyaltyRepository {

    override fun getAllRewards(): Flow<List<Reward>> {
        return loyaltyDao.getAllRewards().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertRewards(rewards: List<Reward>) {
        loyaltyDao.insertRewards(rewards.map { it.toEntity() })
    }

    override fun getAllOffers(): Flow<List<Offer>> {
        return loyaltyDao.getAllOffers().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertOffers(offers: List<Offer>) {
        loyaltyDao.insertOffers(offers.map { it.toEntity() })
    }

    override fun getProfile(): Flow<ProfileInfo?> {
        return loyaltyDao.getProfile().map { it?.toDomain() }
    }

    override suspend fun insertProfile(profile: ProfileInfo) {
        loyaltyDao.insertProfile(profile.toEntity())
    }

    override fun getAllScans(): Flow<List<ScanHistory>> {
        return loyaltyDao.getAllScans().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertScan(scan: ScanHistory) {
        loyaltyDao.insertScan(scan.toEntity())
    }

    override suspend fun deleteScan(scan: ScanHistory) {
        loyaltyDao.deleteScan(scan.toEntity())
    }

    override fun getNotifications(): Flow<List<UserNotification>> {
        return loyaltyDao.getNotifications().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun updateNotification(notification: UserNotification) {
        loyaltyDao.updateNotification(notification.toEntity())
    }

    override suspend fun deleteAllNotifications() {
        loyaltyDao.deleteAllNotifications()
    }
}
