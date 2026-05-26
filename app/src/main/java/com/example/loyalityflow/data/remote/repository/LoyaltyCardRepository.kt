package com.example.loyalityflow.data.remote.repository

import com.example.loyalityflow.data.remote.dto.LoyaltyCardDto

interface LoyaltyCardRepository {

    suspend fun getLoyaltyCards(): Result<List<LoyaltyCardDto>>

    suspend fun createLoyaltyCard(loyaltyCardDto: LoyaltyCardDto): Result<LoyaltyCardDto>

    suspend fun updateLoyaltyCard(id: Int, loyaltyCardDto: LoyaltyCardDto): Result<LoyaltyCardDto>

    suspend fun deleteLoyaltyCard(id: Int): Result<Unit>
}