package com.example.blackjack.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PaletaVerde = lightColorScheme(
    primary = Color(0xFF006C4C),
    background = Color(0xFFF4FBF7),
    surface = Color(0xFFF4FBF7),
    secondaryContainer = Color(0xFFCFE9D9)
)

val PaletaOscura = darkColorScheme(
    primary = Color(0xFF6CDBAC),
    background = Color(0xFF191C1A),
    surface = Color(0xFF191C1A),
    secondaryContainer = Color(0xFF354B40)
)

val PaletaRoja = lightColorScheme(
    primary = Color(0xFFB3261E),
    background = Color(0xFFFFF8F7),
    surface = Color(0xFFFFF8F7),
    secondaryContainer = Color(0xFFFFDAD6)
)

@Composable
fun TemaBlackjack(
    temaSeleccionado: String = "Verde",
    content: @Composable () -> Unit
) {
    val colorScheme = when (temaSeleccionado) {
        "Oscuro" -> PaletaOscura
        "Rojo" -> PaletaRoja
        else -> PaletaVerde
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
