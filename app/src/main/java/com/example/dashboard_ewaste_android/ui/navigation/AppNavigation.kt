package com.example.dashboard_ewaste_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dashboard_ewaste_android.ui.auth.LoginScreen
import com.example.dashboard_ewaste_android.ui.auth.RegisterScreen
import com.example.dashboard_ewaste_android.ui.screens.approval.ApprovalScreen
import com.example.dashboard_ewaste_android.ui.screens.dashboard.DashboardScreen
import com.example.dashboard_ewaste_android.ui.screens.dropbox.DropboxScreen
import com.example.dashboard_ewaste_android.ui.screens.poin.PoinScreen
import com.example.dashboard_ewaste_android.ui.screens.waste.WasteManagementScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Login.route) { // <-- UBAH TITIK AWAL

        // Rute untuk Otentikasi
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // Navigasi ke Dashboard dan hapus semua riwayat navigasi sebelumnya
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
                onRegisterSuccess = {
                    // Navigasi ke Dashboard setelah registrasi berhasil
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onLoginClicked = {
                    navController.popBackStack() // Kembali ke layar login
                }
            )
        }

        // Rute untuk Halaman Utama Aplikasi
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