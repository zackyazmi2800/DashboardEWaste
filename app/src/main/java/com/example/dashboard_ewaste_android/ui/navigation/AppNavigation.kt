package com.example.dashboard_ewaste_android.ui.navigation

import androidx.navigation.compose.composable
import com.example.dashboard_ewaste_android.ui.screens.dashboard.DashboardScreen // PASTIKAN IMPOR INI ADA DAN BENAR
import com.example.dashboard_ewaste_android.ui.screens.dropbox.DropboxScreen
import com.example.dashboard_ewaste_android.ui.screens.poin.PoinScreen
import com.example.dashboard_ewaste_android.ui.screens.approval.ApprovalScreen // PASTIKAN IMPOR INI ADA
import com.example.dashboard_ewaste_android.ui.screens.waste.WasteManagementScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.dashboard_ewaste_android.ui.screens.auth.LoginScreen
import com.example.dashboard_ewaste_android.ui.screens.auth.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Login.route) {
        // Auth Flow
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClicked = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onLoginClicked = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Main App Flow
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