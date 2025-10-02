package com.example.aplicaciondesarrollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicaciondesarrollo.ui.LoginView
import com.example.aplicaciondesarrollo.ui.RegistroView
import com.example.aplicaciondesarrollo.ui.RecuperarClaveView
import com.example.aplicaciondesarrollo.ui.ProfileView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializa Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        // 🔹 Pantalla de Login con Firebase
        composable("login") {
            LoginView(
                onLoginSuccess = { navController.navigate("profile") },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToRecover = { navController.navigate("recover") }
            )
        }

        // 🔹 Pantalla de Registro con Firebase
        composable("register") {
            RegistroView(
                onRegisterSuccess = { navController.navigate("profile") },
                onBack = { navController.popBackStack() }
            )
        }

        // 🔹 Pantalla de Recuperar Contraseña con Firebase
        composable("recover") {
            RecuperarClaveView(onBack = { navController.popBackStack() })
        }

        // 🔹 Pantalla de Perfil
        composable("profile") {
            ProfileView(
                onLogout = {
                    FirebaseAuth.getInstance().signOut()
                    navController.popBackStack("login", inclusive = false)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAppNavigation() {
    AppNavigation()
}
