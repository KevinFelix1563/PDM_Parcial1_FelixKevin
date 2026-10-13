package com.example.blackjack

data class JuegoUiState(
    val manoJugador: List<Carta> = emptyList(),
    val manoCrupier: List<Carta> = emptyList(),
    val puntosJugador: Int = 0,
    val puntosCrupier: Int = 0,
    val estadoPartida: String = "Jugando",
    val meta: Int = 21
)