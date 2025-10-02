package com.example.aplicaciondesarrollo.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirebaseRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun registerUser(
        nombre: String,
        correo: String,
        contrasena: String,
        pais: String
    ): Result<Unit> {
        return try {
            // Crear usuario en Firebase Auth
            val result = auth.createUserWithEmailAndPassword(correo, contrasena).await()
            val uid = result.user?.uid ?: throw Exception("UID nulo")

            // Guardar datos adicionales en Firestore
            val userData = mapOf(
                "nombre" to nombre,
                "correo" to correo,
                "pais" to pais
            )
            db.collection("usuarios").document(uid).set(userData).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
