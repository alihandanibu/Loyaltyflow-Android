package com.example.loyalityflow.data.repository.mappers

import com.example.loyalityflow.data.local.entity.*
import com.example.loyalityflow.data.model.*

fun RewardEntity.toDomain(): Reward = Reward(id, title, description, pointsRequired, category)
fun Reward.toEntity(): RewardEntity = RewardEntity(id, title, description, pointsRequired, category)

fun OfferEntity.toDomain(): Offer = Offer(id, title, description, discountLabel, isActive)
fun Offer.toEntity(): OfferEntity = OfferEntity(id, title, description, discountLabel, isActive)

fun ProfileInfoEntity.toDomain(): ProfileInfo = ProfileInfo(fullName, email, membershipSince, preferredStore, totalScans, totalRewardsRedeemed)
fun ProfileInfo.toEntity(): ProfileInfoEntity = ProfileInfoEntity(email, fullName, membershipSince, preferredStore, totalScans, totalRewardsRedeemed)

fun ScanHistoryEntity.toDomain(): ScanHistory = ScanHistory(scanId, userEmail, date, pointsEarned, storeName)
fun ScanHistory.toEntity(): ScanHistoryEntity = ScanHistoryEntity(scanId, userEmail, date, pointsEarned, storeName)

fun UserNotificationEntity.toDomain(): UserNotification = UserNotification(notificationId, title, message, isRead, timestamp)
fun UserNotification.toEntity(): UserNotificationEntity = UserNotificationEntity(notificationId, title, message, isRead, timestamp)
