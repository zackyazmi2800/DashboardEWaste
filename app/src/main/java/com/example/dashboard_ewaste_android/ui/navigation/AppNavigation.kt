package com.example.dashboard_ewaste_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dashboard_ewaste_android.ui.screens.dashboard.DashboardScreen
import com.example.dashboard_ewaste_android.ui.screens.dropbox.DropboxScreen
import com.example.dashboard_ewaste_android.ui.screens.poin.PoinScreen
import com.example.dashboard_ewaste_android.ui.screens.waste.WasteManagementScreen
import com.example.dashboard_ewaste_android.ui.screens.approval.ApprovalScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Dashboard.route) {
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
        composable(Screen.Waste.route) {
            WasteManagementScreen()
        }
        composable(Screen.Poin.route) {
            PoinScreen()
        }
        composable(Screen.Dropbox.route) {
            DropboxScreen()
        }
        composable(Screen.Approval.route) {
            ApprovalScreen()
        }
    }
}