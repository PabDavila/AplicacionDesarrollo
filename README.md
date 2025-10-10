# 📱 Aplicación de Desarrollo Móvil (Duoc UC)

## 🧩 Descripción
Aplicación desarrollada en **Android Studio (Kotlin + Jetpack Compose)** con navegación, persistencia local (**SQLite - Room**) y personalización de interfaz mediante **modo oscuro** y **tamaño de texto ajustable**.  
El proyecto implementa un flujo completo de **registro, inicio de sesión, recuperación de contraseña y perfil de usuario**.

---

## ⚙️ Tecnologías Utilizadas
- **Kotlin**
- **Jetpack Compose** (UI declarativa)
- **SQLite con Room** (persistencia de datos)
- **DataStore Preferences** (almacenamiento de tema y accesibilidad)
- **Gradle KTS**
- **Material3 + KTX**

---

## 🚀 Funcionalidades Principales
- 🧑 Registro de usuario con validación.
- 🔐 Inicio de sesión con autenticación local (SQLite).
- 🔄 Recuperación de contraseña (simulada).
- 👤 Visualización de perfil.
- 🌗 Interruptor para **modo oscuro/claro**.
- 🔠 Interruptor para **texto grande/normal**.
- 💾 Persistencia de configuraciones con **DataStore**.

---

## 🗂️ Estructura del Proyecto
📁 app/
├── data/
│ ├── AppDatabase.kt
│ ├── Usuario.kt
│ └── UsuarioDao.kt
│
├── ui/
│ ├── LoginView.kt
│ ├── RegistroView.kt
│ ├── RecuperarClaveView.kt
│ ├── ProfileView.kt
│ └── theme/
│ ├── Color.kt
│ ├── Theme.kt
│ └── Type.kt
│
├── MainActivity.kt
└── build.gradle.kts

---

## 🧪 Ejecución del Proyecto

1. Clonar este repositorio:  
   ```bash
   git clone https://github.com/tuusuario/AplicacionDesarrollo.git
2. Abrir el proyecto en Android Studio (versión 2023.1 o superior).

3. Esperar la sincronización de dependencias Gradle.

4. Ejecutar en un emulador API 30 o superior.

5. Registrar un usuario y luego iniciar sesión.

## Autor
**Pablo Dávila Alvarez**  
Duoc UC — Evaluación Final S9
