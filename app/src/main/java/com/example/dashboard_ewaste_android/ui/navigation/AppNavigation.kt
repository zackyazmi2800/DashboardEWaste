package com.example.dashboard_ewaste_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dashboard_ewaste_android.ui.screens.dashboard.DashboardScreen // PASTIKAN IMPOR INI ADA DAN BENAR
import com.example.dashboard_ewaste_android.ui.screens.dropbox.DropboxScreen
import com.example.dashboard_ewaste_android.ui.screens.poin.PoinScreen
import com.example.dashboard_ewaste_android.ui.screens.waste.WasteManagementScreen // PASTIKAN IMPOR INI ADA JIKA FITUR WASTE DIKEMBALIKAN
import com.example.dashboard_ewaste_android.ui.screens.approval.ApprovalScreen // PASTIKAN IMPOR INI ADA

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Dashboard.route) {
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
        composable(Screen.Waste.route) { // BLOK INI HARUS ADA JIKA FITUR WASTE DIKEMBALIKAN
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