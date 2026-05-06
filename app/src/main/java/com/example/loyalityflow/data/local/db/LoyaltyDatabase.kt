package com.example.loyalityflow.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loyalityflow.data.local.dao.LoyaltyDao
import com.example.loyalityflow.data.local.entity.*

/**
 * Main Room database class.
 * Lists all entities and provides access to DAOs.
 */
@Database(
    entities = [
        OfferEntity::class,
        RewardEntity::class,
        ProfileInfoEntity::class,
        ScanHistoryEntity::class,
        UserNotificationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LoyaltyDatabase : RoomDatabase() {
    abstract fun loyaltyDao(): LoyaltyDao
}
