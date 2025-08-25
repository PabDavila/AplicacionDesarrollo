package com.example.aplicaciondesarrollo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicaciondesarrollo.models.Usuario
import com.example.aplicaciondesarrollo.models.usuarios

@Composable
fun RegistroView(onNavigateToLogin: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    // 🔹 CheckBox (aceptar términos)
    var aceptaTerminos by remember { mutableStateOf(false) }

    // 🔹 RadioButtons (género)
    val generos = listOf("Masculino", "Femenino", "Otro")
    var generoSeleccionado by remember { mutableStateOf(generos[0]) }

    // 🔹 ComboBox (país)
    val paises = listOf("Chile", "Argentina", "Perú", "México")
    var paisSeleccionado by remember { mutableStateOf(paises[0]) }
    var expandirDropdown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Registro de Usuario", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        // Inputs básicos
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

        Spacer(Modifier.height(12.dp))

        // 🔹 RadioButtons
        Text("Selecciona tu género:")
        generos.forEach { genero ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (generoSeleccionado == genero),
                    onClick = { generoSeleccionado = genero }
                )
                Text(genero)
            }
        }

        Spacer(Modifier.height(12.dp))

        // 🔹 ComboBox (Dropdown)
        Text("Selecciona tu país:")
        Box {
            OutlinedButton(onClick = { expandirDropdown = true }) {
                Text(paisSeleccionado)
            }
            DropdownMenu(
                expanded = expandirDropdown,
                onDismissRequest = { expandirDropdown = false }
            ) {
                paises.forEach { pais ->
                    DropdownMenuItem(
                        text = { Text(pais) },
                        onClick = {
                            paisSeleccionado = pais
                            expandirDropdown = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // 🔹 CheckBox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = aceptaTerminos,
                onCheckedChange = { aceptaTerminos = it }
            )
            Text("Acepto los términos y condiciones")
        }

        Spacer(Modifier.height(16.dp))

        // Botón Registrar
        Button(
            onClick = {
                if (!aceptaTerminos) {
                    mensaje = "Debes aceptar los términos"
                } else if (password == confirmPassword && nombre.isNotBlank() && email.isNotBlank()) {
                    usuarios.add(Usuario(nombre, email, password))
                    mensaje = "Usuario registrado correctamente"
                    nombre = ""
                    email = ""
                    password = ""
                    confirmPassword = ""
                } else {
                    mensaje = "Error: contraseñas no coinciden o campos vacíos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        Spacer(Modifier.height(8.dp))
        Text(mensaje)

        TextButton(onClick = onNavigateToLogin) {
            Text("Volver al login")
        }

        Spacer(Modifier.height(16.dp))

        // 🔹 Tabla / Grilla de usuarios
        if (usuarios.isNotEmpty()) {
            Text("Usuarios registrados:", fontWeight = FontWeight.Bold)

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Encabezado
                item {
                    Row(
                        Modifier.fillMaxWidth().padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Nombre", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                        Text("Email", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    }
                    Divider()
                }

                // Filas de usuarios
                items(usuarios) { user ->
                    Row(
                        Modifier.fillMaxWidth().padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(user.nombre, modifier = Modifier.weight(1f))
                        Text(user.email, modifier = Modifier.weight(1f))
                    }
                    Divider()
                }
            }
        }
    }
}
