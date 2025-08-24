package com.example.aplicaciondesarrollo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun recuperarClaveView(onNavigateBack: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Recuperar Contraseña")

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                mensaje = "Se envió un enlace a $email"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar")
        }

        Text(mensaje)

        TextButton(onClick = onNavigateBack) {
            Text("Volver al login")
        }
    }
}
