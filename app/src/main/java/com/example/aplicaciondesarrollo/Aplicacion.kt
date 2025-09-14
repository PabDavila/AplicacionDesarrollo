package com.example.aplicaciondesarrollo

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicaciondesarrollo.ui.LoginView
import com.example.aplicaciondesarrollo.ui.RegistroView
import com.example.aplicaciondesarrollo.ui.RecuperarClaveView

@Composable
fun Aplicacion() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginView(
                onLogin = { /* Aquí puedes poner lógica extra si quieres */ },
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAplicacion() {
    Aplicacion()
}
