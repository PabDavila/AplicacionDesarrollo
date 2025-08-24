package com.example.aplicaciondesarrollo.models

import com.example.aplicaciondesarrollo.ui.Usuario

data class Usuario(val nombre: String, val email: String, val password: String)

// Lista compartida entre todas las views
val usuarios = mutableListOf<Usuario>(
    _root_ide_package_.com.example.aplicaciondesarrollo.models.Usuario(
        "Ejemplo 1",
        "ejemplo1@mail.com",
        "1234"
    ),
    _root_ide_package_.com.example.aplicaciondesarrollo.models.Usuario(
        "Ejemplo 2",
        "ejemplo2@mail.com",
        "1234"
    )
    // Puedes precargar algunos
)
