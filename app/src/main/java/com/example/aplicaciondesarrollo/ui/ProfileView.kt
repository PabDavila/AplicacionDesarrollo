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
import com.example.aplicaciondesarrollo.models.usuarios

@OptIn(ExperimentalMaterial3Api::class) // 🔹 Si insistes en usar TopAppBar normal
@Composable
fun ProfileView(
    userEmail: String,
    onLogout: () -> Unit
) {
    val user = usuarios.find { it.email == userEmail }

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

            if (user != null) {
                Text("Nombre: ${user.nombre}", style = MaterialTheme.typography.bodyLarge)
                Text("Correo: ${user.email}", style = MaterialTheme.typography.bodyMedium)
            } else {
                Text("Usuario no encontrado", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onLogout) {
                Text("Cerrar sesión")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileView() {
    ProfileView(
        userEmail = "demo@correo.com",
        onLogout = {}
    )
}
