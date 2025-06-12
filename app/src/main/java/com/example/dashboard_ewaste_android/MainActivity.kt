package com.example.dashboard_ewaste_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.dashboard_ewaste_android.ui.navigation.AppNavigation
import com.example.dashboard_ewaste_android.ui.navigation.BottomNavigationBar
import com.example.dashboard_ewaste_android.ui.theme.DashboardEwasteAndroidTheme

import dagger.hilt.android.AndroidEntryPoint
import android.util.Log // <--- TAMBAHKAN IMPOR INI

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called") // <--- TAMBAHKAN LOG INI
        enableEdgeToEdge()
        setContent {
            DashboardEwasteAndroidTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) {
                    AppNavigation(navController = navController)
                }
            }
        }
    }
}