package com.example.loyalityflow.di

import android.content.Context
import androidx.room.Room
import com.example.loyalityflow.data.local.dao.LoyaltyDao
import com.example.loyalityflow.data.local.db.LoyaltyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LoyaltyDatabase {
        return Room.databaseBuilder(
            context,
            LoyaltyDatabase::class.java,
            "loyalty_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideLoyaltyDao(database: LoyaltyDatabase): LoyaltyDao {
        return database.loyaltyDao()
    }
}
