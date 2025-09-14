package com.example.aplicaciondesarrollo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.aplicaciondesarrollo.models.usuarios

@Composable
fun LoginView(
    onLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToRecover: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar Sesión", fontSize = 22.sp)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val user = usuarios.find { it.email == email && it.password == password }
                if (user != null) {
                    mensaje = "Bienvenido ${user.nombre}"
                    onLogin()
                } else {
                    mensaje = "Usuario o contraseña incorrectos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        Spacer(Modifier.height(8.dp))
        Text(mensaje)

        TextButton(onClick = onNavigateToRegister) {
            Text("¿No tienes cuenta? Registrarse")
        }
        TextButton(onClick = onNavigateToRecover) {
            Text("¿Olvidaste tu contraseña?")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginView() {
    LoginView(onLogin = {}, onNavigateToRegister = {}, onNavigateToRecover = {})
}
