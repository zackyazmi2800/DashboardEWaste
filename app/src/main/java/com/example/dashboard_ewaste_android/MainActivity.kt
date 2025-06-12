package com.example.dashboard_ewaste_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dashboard_ewaste_android.ui.navigation.AppNavigation
import com.example.dashboard_ewaste_android.ui.navigation.BottomNavigationBar
import com.example.dashboard_ewaste_android.ui.navigation.Screen
import com.example.dashboard_ewaste_android.ui.theme.DashboardEwasteAndroidTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardEwasteAndroidTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Tentukan daftar rute yang akan menampilkan Bottom Bar
                val bottomBarRoutes = listOf(
                    Screen.Dashboard.route,
                    Screen.Poin.route,
                    Screen.Dropbox.route,
                    Screen.Approval.route,
                    Screen.Waste.route
                )

                Scaffold(
                    bottomBar = {
                        // Hanya tampilkan BottomNavigationBar jika rute saat ini ada dalam daftar
                        if (currentRoute in bottomBarRoutes) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    // AppNavigation sebagai konten utama
                    AppNavigation(navController = navController)
                }
            }
        }
    }
}