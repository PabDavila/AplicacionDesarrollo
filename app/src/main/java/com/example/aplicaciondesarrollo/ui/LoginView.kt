package com.example.aplicaciondesarrollo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aplicaciondesarrollo.data.AppDatabase
import kotlinx.coroutines.launch

@Composable
fun LoginView(
    onLoginSuccess: (String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToRecover: () -> Unit
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val usuarioDao = db.usuarioDao()
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                scope.launch {
                    val usuario = usuarioDao.autenticar(email, password)
                    if (usuario != null) {
                        mensaje = "Bienvenido ${usuario.nombre}"
                        onLoginSuccess(usuario.correo)
                    } else {
                        mensaje = "Usuario o contraseña incorrectos"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(mensaje, color = MaterialTheme.colorScheme.secondary)

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onNavigateToRegister) {
            Text("¿No tienes cuenta? Regístrate")
        }

        TextButton(onClick = onNavigateToRecover) {
            Text("¿Olvidaste tu contraseña?")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginView() {
    LoginView(
        onLoginSuccess = {},
        onNavigateToRegister = {},
        onNavigateToRecover = {}
    )
}
