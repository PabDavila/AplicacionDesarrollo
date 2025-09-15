package com.example.aplicaciondesarrollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.aplicaciondesarrollo.ui.LoginScreen
import com.example.aplicaciondesarrollo.ui.RegisterScreen
import com.example.aplicaciondesarrollo.ui.RecoverPasswordScreen
import com.example.aplicaciondesarrollo.ui.ProfileScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLogin = { _, _ -> navController.navigate("profile") }, // 🔹 Si login OK → perfil
                onGoToRegister = { navController.navigate("register") },
                onGoToRecover = { navController.navigate("recover") }
            )
        }
        composable("register") {
            RegisterScreen(onBack = { navController.popBackStack() })
        }
        composable("recover") {
            RecoverPasswordScreen(onBack = { navController.popBackStack() })
        }
        composable("profile") {
            ProfileScreen(onLogout = {
                navController.popBackStack("login", inclusive = false)
            })
        }
    }
}
