package com.example.loyalityflow.data.remote.repository

import com.example.loyalityflow.data.remote.api.LoyaltyCardApiService
import com.example.loyalityflow.data.remote.dto.LoyaltyCardDto
import javax.inject.Inject

class LoyaltyCardRepositoryImpl @Inject constructor(
    private val apiService: LoyaltyCardApiService
) : LoyaltyCardRepository {

    override suspend fun getLoyaltyCards(): Result<List<LoyaltyCardDto>> {
        return try {
            val cards = apiService.getLoyaltyCards()
            Result.success(cards)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun createLoyaltyCard(
        loyaltyCardDto: LoyaltyCardDto
    ): Result<LoyaltyCardDto> {
        return try {
            val createdCard = apiService.createLoyaltyCard(loyaltyCardDto)
            Result.success(createdCard)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun updateLoyaltyCard(
        id: Int,
        loyaltyCardDto: LoyaltyCardDto
    ): Result<LoyaltyCardDto> {
        return try {
            val updatedCard = apiService.updateLoyaltyCard(id, loyaltyCardDto)
            Result.success(updatedCard)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun deleteLoyaltyCard(id: Int): Result<Unit> {
        return try {
            apiService.deleteLoyaltyCard(id)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}