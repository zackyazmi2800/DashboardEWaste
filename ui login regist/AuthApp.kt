package com.example.authapp

import androidx.compose.runtime.*
import androidx.compose.material3.*

@Composable
fun AuthApp() {
    var isLoginScreen by remember { mutableStateOf(true) }

    if (isLoginScreen) {
        LoginScreen(onRegisterClicked = { isLoginScreen = false })
    } else {
        RegisterScreen(onLoginClicked = { isLoginScreen = true })
    }
}
