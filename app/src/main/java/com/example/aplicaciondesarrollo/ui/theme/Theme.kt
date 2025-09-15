package com.example.aplicaciondesarrollo.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

// 🎨 Definición de esquema claro
private val LightColors = lightColorScheme(
    primary = BluePrimary,
    onPrimary = White,
    secondary = AccentOrange,
    background = BackgroundLight,
    onBackground = TextPrimary,
    surface = White,
    onSurface = TextPrimary
)

// 🎨 Definición de esquema oscuro
private val DarkColors = darkColorScheme(
    primary = BlueLight,
    onPrimary = White,
    secondary = AccentOrange,
    background = BackgroundDark,
    onBackground = White,
    surface = BlueDark,
    onSurface = White
)

// Definición de Shapes (bordes)
val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),   // Forma pequeña
    medium = RoundedCornerShape(8.dp),  // Forma mediana
    large = RoundedCornerShape(16.dp)   // Forma grande
)

@Composable
fun AplicacionDesarrolloTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Usamos colores predefinidos (sin dynamicColorScheme)
    val colors = if (darkTheme) DarkColors else LightColors

    // 🔹 Forzar color de status bar a juego con el tema
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        window.statusBarColor = colors.primary.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }

    // Usamos los colores predefinidos y Shapes con bordes redondeados
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes, // Aquí pasamos el objeto Shapes
        content = content
    )
}
