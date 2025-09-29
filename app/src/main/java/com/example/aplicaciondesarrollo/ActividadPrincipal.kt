@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.aplicaciondesarrollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FormatSize
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
import com.example.aplicaciondesarrollo.ui.LoginScreen
import com.example.aplicaciondesarrollo.ui.RegisterScreen
import com.example.aplicaciondesarrollo.ui.RecoverPasswordScreen
import com.example.aplicaciondesarrollo.ui.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    var isDarkMode by remember { mutableStateOf(false) }
    var largeFont by remember { mutableStateOf(false) }

    val colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()

    // Ajusta la tipografía según largeFont
    val typography = Typography().run {
        copy(
            bodyLarge = bodyLarge.copy(fontSize = if (largeFont) 20.sp else 16.sp),
            bodyMedium = bodyMedium.copy(fontSize = if (largeFont) 18.sp else 14.sp),
            headlineSmall = headlineSmall.copy(fontSize = if (largeFont) 28.sp else 22.sp)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ) {
        AppScaffold(
            isDarkMode = isDarkMode,
            onDarkModeChange = { isDarkMode = it },
            largeFont = largeFont,
            onFontSizeChange = { largeFont = it }
        )
    }
}

@Composable
fun AppScaffold(
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    largeFont: Boolean,
    onFontSizeChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            // Usamos SmallTopAppBar (está estable en Material3)
            SmallTopAppBar(
                title = { Text("Aplicación Desarrollo") },
                actions = {
                    // Switch para tamaño de fuente (izquierda)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(Icons.Default.FormatSize, contentDescription = "Tamaño de fuente")
                        Switch(
                            checked = largeFont,
                            onCheckedChange = { onFontSizeChange(it) }
                        )
                    }

                    // Switch para modo oscuro (derecha)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(Icons.Default.DarkMode, contentDescription = "Modo oscuro")
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { onDarkModeChange(it) }
                        )
                    }
                }
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(padding)
        ) {
            composable("login") {
                LoginScreen(
                    onLogin = { _, _ -> navController.navigate("profile") },
                    onGoToRegister = { navController.navigate("register") },
                    onGoToRecover = { navController.navigate("recover") }
                )
            }
            composable("register") {
                RegisterScreen(
                    onRegister = { nombre, correo, pass1, pass2, pais, acepta ->
                        if (nombre.isNotBlank() && correo.isNotBlank() && pass1 == pass2 && acepta) {
                            navController.navigate("profile")
                        }
                    },
                    onBack = { navController.popBackStack() }
                )
            }
            composable("recover") {
                RecoverPasswordScreen(onBack = { navController.popBackStack() })
            }
            composable("profile") {
                // PASAMOS valores mínimos para evitar el error "No value passed..."
                // Cambia "Demo User" / "demo@correo.com" por valores reales cuando los tengas
                ProfileScreen(
                    userName = "Demo User",
                    userEmail = "demo@correo.com",
                    onLogout = {
                        navController.popBackStack("login", inclusive = false)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMyApp() {
    MyApp()
}
