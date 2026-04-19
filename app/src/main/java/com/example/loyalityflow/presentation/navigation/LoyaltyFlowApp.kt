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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.loyalityflow.presentation.ui.screens.home.HomeScreen
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
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
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
            // innerPadding is used to avoid the bottom navigation bar overlapping content
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
                RewardsScreen(
                    onRewardClick = { rewardId ->
                        navController.navigate(Screen.RewardDetails.createRoute(rewardId))
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(Screen.RewardDetails.route) { backStackEntry ->
                // Parsing ID from the route argument
                val rewardId = backStackEntry.arguments?.getString("rewardId")?.toIntOrNull() ?: 0
                RewardDetailsScreen(
                    rewardId = rewardId,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
