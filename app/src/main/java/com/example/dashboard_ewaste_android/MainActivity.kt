package com.example.dashboard_ewaste_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
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

                val mainAppScreens = listOf(
                    Screen.Dashboard.route,
                    Screen.Waste.route,
                    Screen.Poin.route,
                    Screen.Dropbox.route
                )

                Scaffold(
                    bottomBar = {
                        if (currentRoute in mainAppScreens) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    // AppNavigation tidak lagi memerlukan innerPadding karena
                    // halaman login/register tidak berada dalam Scaffold utama
                    AppNavigation(navController = navController)
                }
            }
        }
    }
}