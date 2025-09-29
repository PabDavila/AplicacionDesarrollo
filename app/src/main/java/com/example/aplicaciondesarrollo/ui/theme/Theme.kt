package com.example.aplicaciondesarrollo.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

private val LightColors = lightColorScheme(
    primary = Color(0xFF007BFF),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6),
    background = Color(0xFFF2F2F2),
    surface = Color.White,
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF1E88E5),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White
)

@Composable
fun AppTheme(
    darkTheme: Boolean,
    largeFont: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    val baseTypography = Typography()

    val typography = baseTypography.copy(
        bodyLarge = baseTypography.bodyLarge.copy(
            fontSize = if (largeFont) 20.sp else 16.sp
        ),
        bodyMedium = baseTypography.bodyMedium.copy(
            fontSize = if (largeFont) 18.sp else 14.sp
        ),
        headlineSmall = baseTypography.headlineSmall.copy(
            fontSize = if (largeFont) 28.sp else 22.sp
        )
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content
    )
}
