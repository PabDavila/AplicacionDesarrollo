package com.example.aplicaciondesarrollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicaciondesarrollo.ui.*

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
    var loggedUserEmail by remember { mutableStateOf<String?>(null) }

    NavHost(navController = navController, startDestination = "login") {

        // 🔹 Pantalla de Login
        composable("login") {
            LoginView(
                onLoginSuccess = { email ->
                    loggedUserEmail = email
                    navController.navigate("profile")
                },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToRecover = { navController.navigate("recover") }
            )
        }

        // 🔹 Pantalla de Registro
        composable("register") {
            RegistroView(
                onRegisterSuccess = { navController.navigate("login") },
                onBack = { navController.popBackStack() }
            )
        }

        // 🔹 Pantalla de Recuperar contraseña
        composable("recover") {
            RecuperarClaveView(
                onBack = { navController.popBackStack() }
            )
        }

        // 🔹 Pantalla de Perfil
        composable("profile") {
            ProfileView(
                userEmail = loggedUserEmail ?: "demo@correo.com",
                onLogout = {
                    loggedUserEmail = null
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
    }
}
