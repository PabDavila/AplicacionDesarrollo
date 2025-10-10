package com.example.aplicaciondesarrollo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import com.example.aplicaciondesarrollo.data.AppDatabase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileView(
    userEmail: String,
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val usuarioDao = db.usuarioDao()
    val scope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf<String?>(null) }
    var correo by remember { mutableStateOf(userEmail) }

    // 🔹 Cargar datos desde SQLite
    LaunchedEffect(userEmail) {
        scope.launch {
            val usuario = usuarioDao.buscarPorCorreo(userEmail)
            nombre = usuario?.nombre
        }
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Mi Perfil") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Avatar",
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Nombre: ${nombre ?: "Cargando..."}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Correo: $correo",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { onLogout() }) {
                Text("Cerrar sesión")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileView() {
    ProfileView(userEmail = "demo@correo.com", onLogout = {})
}
