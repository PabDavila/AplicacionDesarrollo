package com.example.aplicaciondesarrollo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicaciondesarrollo.ui.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// DataStore a nivel de contexto
val Context.dataStore by preferencesDataStore(name = "user_prefs")

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
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // 🔹 Claves para guardar las preferencias
    val darkThemeKey = booleanPreferencesKey("dark_theme")
    val largeTextKey = booleanPreferencesKey("large_text")

    // 🔹 Estados del tema y tamaño
    var isDarkTheme by remember { mutableStateOf(false) }
    var isLargeText by remember { mutableStateOf(false) }
    var loggedUserEmail by remember { mutableStateOf<String?>(null) }

    // 🔹 Cargar preferencias guardadas al iniciar
    LaunchedEffect(Unit) {
        val prefs = context.dataStore.data.first()
        isDarkTheme = prefs[darkThemeKey] ?: false
        isLargeText = prefs[largeTextKey] ?: false
    }

    val navController = rememberNavController()

    // 🔹 Aplica el tema dinámico
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
                    title = { Text("Aplicación Desarrollo") },
                    actions = {
                        // 🔹 Switch modo oscuro
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🌙", fontSize = 18.sp)
                            Switch(
                                checked = isDarkTheme,
                                onCheckedChange = {
                                    isDarkTheme = it
                                    scope.launch {
                                        context.dataStore.edit { prefs ->
                                            prefs[darkThemeKey] = it
                                        }
                                    }
                                }
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // 🔹 Switch tamaño de texto
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("A+", fontSize = 18.sp)
                            Switch(
                                checked = isLargeText,
                                onCheckedChange = {
                                    isLargeText = it
                                    scope.launch {
                                        context.dataStore.edit { prefs ->
                                            prefs[largeTextKey] = it
                                        }
                                    }
                                }
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavHost(navController = navController, startDestination = "login") {

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

                    composable("register") {
                        RegistroView(
                            onRegisterSuccess = { navController.navigate("login") },
                            onBack = { navController.popBackStack() }
                        )
                    }

                    composable("recover") {
                        RecuperarClaveView(onBack = { navController.popBackStack() })
                    }

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
