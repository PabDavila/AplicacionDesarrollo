package com.example.aplicaciondesarrollo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(usuario: Usuario)

    // Buscar usuario por correo (para validar duplicados en el registro)
    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    suspend fun buscarPorCorreo(correo: String): Usuario?

    // Autenticar usuario (para usar en LoginView)
    @Query("SELECT * FROM usuarios WHERE correo = :correo AND contrasena = :contrasena LIMIT 1")
    suspend fun autenticar(correo: String, contrasena: String): Usuario?

    // (Opcional) Obtener todos los usuarios
    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodos(): List<Usuario>
}
