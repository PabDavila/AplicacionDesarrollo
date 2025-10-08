package com.example.aplicaciondesarrollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicaciondesarrollo.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigationWithSettings()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationWithSettings() {
    // 🔹 Estados globales para tema y tamaño de texto
    var isDarkTheme by remember { mutableStateOf(false) }
    var isLargeText by remember { mutableStateOf(false) }
    var loggedUserEmail by remember { mutableStateOf<String?>(null) }

    // 🔹 Controlador de navegación
    val navController = rememberNavController()

    // 🔹 Aplica tema dinámico (oscuro o claro)
    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme(),
        typography = if (isLargeText) {
            Typography(
                bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontSize = 26.sp)
            )
        } else {
            Typography()
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    actions = {
                        // Switch Modo oscuro
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🌙", fontSize = 18.sp)
                            Switch(
                                checked = isDarkTheme,
                                onCheckedChange = { isDarkTheme = it }
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))

                        // Switch Tamaño de texto
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("A+", fontSize = 18.sp)
                            Switch(
                                checked = isLargeText,
                                onCheckedChange = { isLargeText = it }
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                // 🔹 Contenedor de navegación
                NavHost(navController = navController, startDestination = "login") {
                    // Login
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

                    // Registro
                    composable("register") {
                        RegistroView(
                            onRegisterSuccess = { navController.navigate("login") },
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // Recuperar contraseña
                    composable("recover") {
                        RecuperarClaveView(onBack = { navController.popBackStack() })
                    }

                    // Perfil
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
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAppNavigationWithSettings() {
    AppNavigationWithSettings()
}
