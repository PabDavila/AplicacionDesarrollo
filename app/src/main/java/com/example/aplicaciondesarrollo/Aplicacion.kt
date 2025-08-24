package com.example.aplicaciondesarrollo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicaciondesarrollo.ui.*

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginView(
                onLogin = { /* Aquí validas usuario si quieres */ },
                onNavigateToRegister = { navController.navigate("registro") },
                onNavigateToRecover = { navController.navigate("recuperar") }
            )

        }
        composable("registro") {
            RegistroView(
                onNavigateToLogin = { navController.popBackStack("login", false) }
            )
        }
        composable("recuperar") {
            RecuperarClaveView(
                onNavigateBack = { navController.popBackStack("login", false) }
            )
        }
    }
}
