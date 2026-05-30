package com.example.loyalityflow.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoyaltyCardDto(
    val userId: Int = 1,
    val id: Int = 0,

    @SerializedName("title")
    val storeName: String = "",

    @SerializedName("body")
    val description: String = ""
)
