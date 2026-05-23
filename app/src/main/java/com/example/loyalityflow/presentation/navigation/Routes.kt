package com.example.loyalityflow.presentation.navigation

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Screen definitions for the app.
 * We use a sealed class to manage routes in a type-safe way.
 */
sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    data object Home : Screen("home", "Home", Icons.Filled.Home)
    data object Offers : Screen("offers", "Offers", Icons.Filled.List)
    data object Scan : Screen("scan", "Scan", Icons.Filled.AddCircle)
    data object Rewards : Screen("rewards", "Rewards", Icons.Filled.Star)
    data object Profile : Screen("profile", "Profile", Icons.Filled.Person)
    
    // Nested route for details, passing two arguments
    data object RewardDetails : Screen("reward_details/{rewardId}/{rewardTitle}", "Reward details") {
        fun createRoute(rewardId: Int, rewardTitle: String): String =
            "reward_details/$rewardId/${Uri.encode(rewardTitle)}"
    }
}

// Items displayed in the Bottom Navigation Bar
val bottomNavItems = listOf(
    Screen.Home,
    Screen.Offers,
    Screen.Scan,
    Screen.Rewards,
    Screen.Profile
)
