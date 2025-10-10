package com.example.aplicaciondesarrollo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicaciondesarrollo.data.AppDatabase
import com.example.aplicaciondesarrollo.data.Usuario
import kotlinx.coroutines.launch

@Composable
fun RegistroView(
    onRegisterSuccess: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val usuarioDao = db.usuarioDao()
    val scope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro de Usuario", fontSize = 22.sp)

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )

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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                when {
                    nombre.isEmpty() || email.isEmpty() || password.isEmpty() -> {
                        mensaje = "Completa todos los campos"
                    }
                    password != confirmPassword -> {
                        mensaje = "Las contraseñas no coinciden"
                    }
                    else -> {
                        scope.launch {
                            val existente = usuarioDao.buscarPorCorreo(email)
                            if (existente != null) {
                                mensaje = "Ya existe un usuario con este correo"
                            } else {
                                usuarioDao.insertar(
                                    Usuario(
                                        nombre = nombre,
                                        correo = email,
                                        contrasena = password
                                    )
                                )
                                mensaje = "Usuario registrado correctamente"
                                onRegisterSuccess()
                            }
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        Spacer(Modifier.height(8.dp))
        Text(mensaje, color = MaterialTheme.colorScheme.secondary)

        TextButton(onClick = onBack) {
            Text("Volver al login")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegistroView() {
    RegistroView(onRegisterSuccess = {}, onBack = {})
}
