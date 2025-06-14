package com.example.dashboard_ewaste_android.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String? = null, val icon: ImageVector? = null) {
    // Auth Screens (tidak perlu title dan icon karena tidak akan ditampilkan di Bottom Bar)
    object Login : Screen("login")
    object Register : Screen("register")

    // Main App Screens
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Default.Dashboard)
    object Waste : Screen("waste", "Sampah", Icons.Default.Delete)
    object Poin : Screen("poin", "Poin", Icons.Default.Star)
    object Dropbox : Screen("dropbox", "Dropbox", Icons.Default.Inventory)
    object Approval : Screen("approval", "Approval", Icons.Default.CheckCircle)
}