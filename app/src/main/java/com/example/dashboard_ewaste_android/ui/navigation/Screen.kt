package com.example.dashboard_ewaste_android.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Default.Dashboard)
    object Waste : Screen("waste", "Sampah", Icons.Default.Delete)
    object Poin : Screen("poin", "Poin", Icons.Default.Star)
    object Dropbox : Screen("dropbox", "Dropbox", Icons.Default.Inventory)
}