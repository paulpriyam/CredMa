package com.example.credma.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Card : BottomNavItem("card", "Cards", Icons.Default.Home)
    object AllCards : BottomNavItem("all_cards", "All Cards", Icons.Default.List)
    object AllTransactions : BottomNavItem("all_transactions", "Transactions", Icons.Default.DateRange)
    object Settings : BottomNavItem("settings", "Settings", Icons.Default.Settings)
}
