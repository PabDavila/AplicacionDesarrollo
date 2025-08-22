package com.example.aplicaciondesarrollo.models
data class Usuario(val nombre: String, val email: String, val password: String)

// Lista compartida entre todas las views
val usuarios = mutableListOf<Usuario>(
    Usuario("Ejemplo 1", "ejemplo1@mail.com", "1234"),
    Usuario("Ejemplo 2", "ejemplo2@mail.com", "1234")
    // Puedes precargar algunos
)
