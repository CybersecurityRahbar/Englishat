package com.englishat.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EnglishatLightColors = lightColorScheme(
    primary = Color(0xFF245BDB),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE7EEFF),
    onPrimaryContainer = Color(0xFF112B68),
    secondary = Color(0xFF16A58A),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE0F7F1),
    onSecondaryContainer = Color(0xFF075C4B),
    background = Color(0xFFF5F7FC),
    onBackground = Color(0xFF17223B),
    surface = Color.White,
    onSurface = Color(0xFF17223B),
    surfaceVariant = Color(0xFFF0F3F9),
    onSurfaceVariant = Color(0xFF68748C),
    outline = Color(0xFFE3E8F1),
    error = Color(0xFFBD3F4B)
)

@Composable
fun EnglishatTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = EnglishatLightColors, content = content)
}
