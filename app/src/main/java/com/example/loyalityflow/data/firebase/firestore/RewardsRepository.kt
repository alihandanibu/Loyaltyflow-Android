package com.example.loyalityflow.data.firebase.firestore

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RewardsRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) {
    fun observeRewards(): Flow<Result<List<RewardDto>>> = callbackFlow {
        val userId = firebaseAuth.currentUser?.uid

        if (userId == null) {
            trySend(Result.failure(Exception("User is not logged in")))
            close()
            return@callbackFlow
        }

        val listenerRegistration = firestore
            .collection("user_rewards")
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Result.failure(error))
                    return@addSnapshotListener
                }

                val rewards = snapshot?.documents?.mapNotNull { document ->
                    document.toObject(RewardDto::class.java)?.copy(
                        id = document.id
                    )
                } ?: emptyList()

                trySend(Result.success(rewards))
            }

        awaitClose {
            listenerRegistration.remove()
        }
    }

    suspend fun addDemoReward(): Result<Unit> {
        return try {
            val userId = firebaseAuth.currentUser?.uid
                ?: return Result.failure(Exception("User is not logged in"))

            val reward = hashMapOf(
                "title" to "Welcome reward",
                "points" to 50,
                "userId" to userId
            )

            firestore.collection("user_rewards")
                .add(reward)
                .await()

            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    suspend fun deleteReward(id: String): Result<Unit> {
        return try {
            firestore.collection("user_rewards")
                .document(id)
                .delete()
                .await()

            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
