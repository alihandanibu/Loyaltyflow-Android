package com.example.loyalityflow.presentation.navigation

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object Offers : Screen("offers", "Offers", Icons.Filled.List)
    data object Scan : Screen("scan", "Scan", Icons.Filled.AddCircle)
    data object Rewards : Screen("rewards", "Rewards", Icons.Filled.Star)
    data object Profile : Screen("profile", "Profile", Icons.Filled.Person)
    data object NetworkCards : Screen("network_cards", "Network", Icons.Filled.Refresh)
    data object Auth : Screen("auth", "Auth", Icons.Filled.Lock)
    
    data object RewardDetails : Screen("reward_details/{rewardId}/{rewardTitle}", "Reward details") {
        fun createRoute(rewardId: Int, rewardTitle: String): String =
            "reward_details/$rewardId/${Uri.encode(rewardTitle)}"
    }
}

// Ovdje držimo samo 5 glavnih stavki za čist UI
val bottomNavItems = listOf(
    Screen.Home,
    Screen.Offers,
    Screen.Scan,
    Screen.Rewards,
    Screen.Profile
)
