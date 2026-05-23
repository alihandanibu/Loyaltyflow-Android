package com.example.loyalityflow.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loyalityflow.data.model.DashboardStats
import com.example.loyalityflow.data.repository.LoyaltyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LoyaltyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                repository.getProfile()
                    .filterNotNull() // Sačekaj dok profil ne bude dostupan u bazi
                    .flatMapLatest { profile ->
                        repository.getAllOffers().map { offers ->
                            val stats = DashboardStats(
                                userName = profile.fullName,
                                totalPoints = profile.totalScans * 10,
                                tier = "Gold",
                                nextRewardTitle = "Free Coffee"
                            )
                            
                            val points = stats.totalPoints
                            HomeUiState.Success(
                                stats = stats,
                                featuredOffers = offers,
                                pointsToNextReward = if (points < 2000) 2000 - points else 0,
                                isGoldTier = stats.tier == "Gold",
                                canRedeemSomething = points >= 50
                            )
                        }
                    }
                    .collect { state ->
                        _uiState.value = state
                    }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addDemoPoints() {
        viewModelScope.launch {
            val currentProfile = repository.getProfile().first()
            currentProfile?.let {
                repository.insertProfile(it.copy(totalScans = it.totalScans + 5))
            }
        }
    }
}
