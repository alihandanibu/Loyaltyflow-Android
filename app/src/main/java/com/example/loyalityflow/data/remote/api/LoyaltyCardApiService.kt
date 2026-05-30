package com.example.loyalityflow.data.remote.api

import com.example.loyalityflow.data.remote.dto.LoyaltyCardDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LoyaltyCardApiService {

    @GET("posts")
    suspend fun getLoyaltyCards(): List<LoyaltyCardDto>

    @POST("posts")
    suspend fun createLoyaltyCard(
        @Body loyaltyCardDto: LoyaltyCardDto
    ): LoyaltyCardDto

    @PUT("posts/{id}")
    suspend fun updateLoyaltyCard(
        @Path("id") id: Int,
        @Body loyaltyCardDto: LoyaltyCardDto
    ): LoyaltyCardDto

    @DELETE("posts/{id}")
    suspend fun deleteLoyaltyCard(
        @Path("id") id: Int
    )
}
