package com.example.loyalityflow.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loyalityflow.presentation.ui.screens.auth.AuthScreen
import com.example.loyalityflow.presentation.ui.screens.home.HomeScreen
import com.example.loyalityflow.presentation.ui.screens.network.LoyaltyCardsNetworkScreen
import com.example.loyalityflow.presentation.ui.screens.offers.OffersScreen
import com.example.loyalityflow.presentation.ui.screens.profile.ProfileScreen
import com.example.loyalityflow.presentation.ui.screens.rewarddetails.RewardDetailsScreen
import com.example.loyalityflow.presentation.ui.screens.rewards.RewardsScreen
import com.example.loyalityflow.presentation.ui.screens.scan.ScanScreen

@Composable
fun LoyaltyFlowApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { screen ->
                    val isSelected = currentRoute == screen.route
                    NavigationBarItem(
                        icon = {
                            screen.icon?.let { icon ->
                                Icon(
                                    imageVector = icon,
                                    contentDescription = screen.title
                                )
                            }
                        },
                        label = { Text(screen.title) },
                        selected = isSelected,
                        alwaysShowLabel = false, // Centriranje ikonica
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToScan = { navController.navigate(Screen.Scan.route) },
                    onNavigateToRewards = { navController.navigate(Screen.Rewards.route) }
                )
            }

            composable(Screen.Offers.route) {
                OffersScreen()
            }

            composable(Screen.Scan.route) {
                ScanScreen()
            }

            composable(Screen.Rewards.route) {
                RewardsScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    onNavigateToAuth = { navController.navigate(Screen.Auth.route) },
                    onNavigateToNetwork = { navController.navigate(Screen.NetworkCards.route) }
                )
            }

            composable(Screen.NetworkCards.route) {
                LoyaltyCardsNetworkScreen()
            }

            composable(Screen.Auth.route) {
                AuthScreen()
            }

            composable(
                route = Screen.RewardDetails.route,
                arguments = listOf(
                    navArgument("rewardId") { type = NavType.IntType },
                    navArgument("rewardTitle") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val rewardId = backStackEntry.arguments?.getInt("rewardId") ?: 0
                val rewardTitle = backStackEntry.arguments?.getString("rewardTitle").orEmpty()
                RewardDetailsScreen(
                    rewardId = rewardId,
                    rewardTitle = rewardTitle,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
