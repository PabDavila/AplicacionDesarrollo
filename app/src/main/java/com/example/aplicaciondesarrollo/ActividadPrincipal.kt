package com.example.aplicaciondesarrollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicaciondesarrollo.ui.LoginView
import com.example.aplicaciondesarrollo.ui.RegistroView
import com.example.aplicaciondesarrollo.ui.RecuperarClaveView
import com.example.aplicaciondesarrollo.ui.ProfileView
import com.example.aplicaciondesarrollo.ui.theme.AppTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            AppWithSettings()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppWithSettings() {
    // estados globales de configuración (persisten en rotación)
    var darkTheme by rememberSaveable { mutableStateOf(false) }
    var largeFont by rememberSaveable { mutableStateOf(false) }
    val navController = rememberNavController()

    // Aplica el theme que adapta colores y tipografías según darkTheme / largeFont
    AppTheme(darkTheme = darkTheme, largeFont = largeFont) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    actions = {
                        // Aquí van los switches: izquierda = tamaño, derecha = tema
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            // Switch para tamaño de fuente
                            Text(
                                text = "A",
                                fontSize = if (largeFont) 18.sp else 14.sp,
                                modifier = Modifier.padding(end = 6.dp)
                            )
                            Switch(
                                checked = largeFont,
                                onCheckedChange = { largeFont = it },
                                modifier = Modifier.padding(end = 12.dp)
                            )

                            // Switch para modo oscuro
                            Text(
                                text = if (darkTheme) "Oscuro" else "Claro",
                                modifier = Modifier.padding(end = 6.dp)
                            )
                            Switch(
                                checked = darkTheme,
                                onCheckedChange = { darkTheme = it }
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "login",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("login") {
                    LoginView(
                        onLoginSuccess = { navController.navigate("profile") },
                        onNavigateToRegister = { navController.navigate("register") },
                        onNavigateToRecover = { navController.navigate("recover") }
                    )
                }
                composable("register") {
                    RegistroView(
                        onRegisterSuccess = { navController.navigate("profile") },
                        onBack = { navController.popBackStack() }
                    )
                }
                composable("recover") {
                    RecuperarClaveView(onBack = { navController.popBackStack() })
                }
                composable("profile") {
                    // ProfileView actualmente toma onLogout() y obtiene usuario desde FirebaseAuth
                    ProfileView(onLogout = {
                        FirebaseAuth.getInstance().signOut()
                        navController.popBackStack("login", inclusive = false)
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAppWithSettings() {
    // Preview con valores por defecto
    AppTheme(darkTheme = false, largeFont = false) {
        AppWithSettings()
    }
}
