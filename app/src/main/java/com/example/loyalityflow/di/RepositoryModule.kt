package com.example.loyalityflow.di

import com.example.loyalityflow.data.repository.LoyaltyRepository
import com.example.loyalityflow.data.repository.LoyaltyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoyaltyRepository(
        loyaltyRepositoryImpl: LoyaltyRepositoryImpl
    ): LoyaltyRepository
}
