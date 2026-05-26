package com.example.loyalityflow.data.remote.api

import com.example.loyalityflow.data.remote.dto.LoyaltyCardDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LoyaltyCardApiService {

    @GET("loyalty-cards")
    suspend fun getLoyaltyCards(): List<LoyaltyCardDto>

    @POST("loyalty-cards")
    suspend fun createLoyaltyCard(
        @Body loyaltyCardDto: LoyaltyCardDto
    ): LoyaltyCardDto

    @PUT("loyalty-cards/{id}")
    suspend fun updateLoyaltyCard(
        @Path("id") id: Int,
        @Body loyaltyCardDto: LoyaltyCardDto
    ): LoyaltyCardDto

    @DELETE("loyalty-cards/{id}")
    suspend fun deleteLoyaltyCard(
        @Path("id") id: Int
    )
}