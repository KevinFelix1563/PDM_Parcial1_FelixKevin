package com.example.blackjack

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class JuegoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(JuegoUiState())
    val uiState: StateFlow<JuegoUiState> = _uiState.asStateFlow()

    private var barajaActual: MutableList<Carta> = mutableListOf()

    fun iniciarPartida(metaSeleccionada: Int) {
        barajaActual = BarajaProvider.obtenerBarajaMezclada()
        
        val cartasJugador = listOf(barajaActual.removeAt(0), barajaActual.removeAt(0))
        val cartasCrupier = listOf(barajaActual.removeAt(0), barajaActual.removeAt(0))

        _uiState.value = JuegoUiState(
            manoJugador = cartasJugador,
            manoCrupier = cartasCrupier,
            puntosJugador = calcularPuntos(cartasJugador, metaSeleccionada),
            puntosCrupier = calcularPuntos(cartasCrupier, metaSeleccionada),
            estadoPartida = "Jugando",
            meta = metaSeleccionada
        )
    }

    private fun calcularPuntos(mano: List<Carta>, meta: Int): Int {
        var suma = mano.sumOf { it.valorBase }
        var ases = mano.count { it.valorBase == 11 }

        while (suma > meta && ases > 0) {
            suma -= 10
            ases--
        }
        return suma
    }

    fun pedirCartaJugador() {
        if (_uiState.value.estadoPartida != "Jugando") return

        val nuevaCarta = barajaActual.removeAt(0)
        val nuevaMano = _uiState.value.manoJugador + nuevaCarta
        val nuevosPuntos = calcularPuntos(nuevaMano, _uiState.value.meta)

        if (nuevosPuntos > _uiState.value.meta) {
            _uiState.update {
                it.copy(
                    manoJugador = nuevaMano,
                    puntosJugador = nuevosPuntos,
                    estadoPartida = "Gana Crupier"
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    manoJugador = nuevaMano,
                    puntosJugador = nuevosPuntos
                )
            }
        }
    }

    fun plantarse() {
        if (_uiState.value.estadoPartida != "Jugando") return

        var manoCrupierActual = _uiState.value.manoCrupier
        var puntosCrupierActual = calcularPuntos(manoCrupierActual, _uiState.value.meta)
        val limiteCrupier = _uiState.value.meta - 4

        while (puntosCrupierActual < limiteCrupier) {
            val nuevaCarta = barajaActual.removeAt(0)
            manoCrupierActual = manoCrupierActual + nuevaCarta
            puntosCrupierActual = calcularPuntos(manoCrupierActual, _uiState.value.meta)
        }

        val puntosJugador = _uiState.value.puntosJugador
        val estadoFinal = when {
            puntosCrupierActual > _uiState.value.meta -> "Gana Jugador"
            puntosCrupierActual > puntosJugador -> "Gana Crupier"
            puntosCrupierActual == puntosJugador -> "Empate"
            else -> "Gana Jugador"
        }

        _uiState.update {
            it.copy(
                manoCrupier = manoCrupierActual,
                puntosCrupier = puntosCrupierActual,
                estadoPartida = estadoFinal
            )
        }
    }
}
