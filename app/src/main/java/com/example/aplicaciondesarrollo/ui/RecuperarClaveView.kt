package com.example.aplicaciondesarrollo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecuperarClaveView(onNavigateBack: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Recuperar Contraseña", fontSize = 22.sp)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (email.isNotBlank() && email.contains("@")) {
                    mensaje = "Se ha enviado un correo de recuperación a $email"
                } else {
                    mensaje = "Correo inválido"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar")
        }

        Spacer(Modifier.height(8.dp))
        Text(mensaje)

        TextButton(onClick = onNavigateBack) {
            Text("Volver al login")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRecuperarClaveView() {
    RecuperarClaveView(onNavigateBack = {})
}
