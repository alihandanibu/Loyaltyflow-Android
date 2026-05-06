package com.example.loyalityflow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loyalityflow.data.model.*

@Database(
    entities = [
        Offer::class,
        Reward::class,
        ProfileInfo::class,
        ScanHistory::class,
        UserNotification::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LoyaltyDatabase : RoomDatabase() {
    abstract fun loyaltyDao(): LoyaltyDao
}
