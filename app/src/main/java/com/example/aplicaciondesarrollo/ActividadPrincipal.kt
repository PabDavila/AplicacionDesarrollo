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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplicaciondesarrollo.ui.ProfileView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        // 🔹 Pantalla de Login
        composable("login") {
            LoginScreen(
                onLogin = { _, _ -> navController.navigate("profile") }, // Si login OK → perfil
                onGoToRegister = { navController.navigate("register") },
                onGoToRecover = { navController.navigate("recover") }
            )
        }

        // 🔹 Pantalla de Registro
        composable("register") {
            RegisterScreen(
                onRegister = { nombre, correo, pass1, pass2, pais, acepta ->
                    if (nombre.isNotBlank() && correo.isNotBlank() && pass1 == pass2 && acepta) {
                        navController.navigate("profile")
                    }
                },
                onBack = { navController.popBackStack() } // 🔹 ahora sí pasamos el onBack
            )
        }


        // 🔹 Pantalla de Recuperación
        composable("recover") {
            RecoverPasswordScreen(onBack = { navController.popBackStack() })
        }

        // 🔹 Pantalla de Perfil
        composable("profile") {
            ProfileView(
                userEmail = "demo@correo.com", // correo fijo de ejemplo
                onLogout = {
                    navController.popBackStack("login", inclusive = false)
                }
            )
        }
    }
}



