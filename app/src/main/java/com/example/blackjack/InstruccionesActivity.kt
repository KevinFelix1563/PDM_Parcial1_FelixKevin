package com.example.blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.blackjack.ui.theme.BlackjackTheme

class InstruccionesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlackjackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "Instrucciones",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Text(text = "1. Objetivo del Juego", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(
                            text = "El objetivo principal del Blackjack es vencer al crupier (la casa). Para lograrlo, debes obtener una mano cuyo valor sume una cantidad más cercana a la meta establecida (tradicionalmente 21, pero ajustable en esta versión a 11 o 25) sin pasarte de dicho límite. Si tu mano supera la meta, se considera 'Busto' (Bust) y pierdes automáticamente la ronda, sin importar lo que tenga el crupier.",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Text(text = "2. Valor de las Cartas", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(
                            text = "La baraja inglesa estándar no utiliza comodines en este juego. Los valores son estrictos:\n- Cartas del 2 al 10: Conservan su valor numérico exacto.\n- Figuras (J, Q, K): Cada una tiene un valor fijo de 10 puntos.\n- El As (A): Es la carta más versátil. Puede valer 1 u 11 puntos, dependiendo de lo que más beneficie a tu mano en ese momento para no pasarte del límite.",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Text(text = "3. Mecánica del Turno del Jugador", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(
                            text = "Al inicio, tanto tú como el crupier reciben dos cartas. Tus cartas son visibles. En tu turno, tienes dos acciones principales:\n- Pedir Carta (Hit): Solicitas una carta adicional para acercarte a la meta. Puedes pedir tantas como desees mientras no te pases.\n- Plantarse (Stand): Decides quedarte con tu mano actual y ceder el turno al crupier.",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Text(text = "4. Reglas Estrictas del Crupier", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(
                            text = "El crupier no toma decisiones libres; juega bajo reglas fijas del casino:\n- Una vez que te plantas, el crupier revela su carta oculta.\n- Si su mano suma 16 puntos o menos, está obligado a 'Pedir' otra carta.\n- Si su mano suma 17 puntos o más, está obligado a 'Plantarse'. (Nota: En nuestra meta dinámica, esta regla se ajusta proporcionalmente a la meta seleccionada).",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Text(text = "5. Condiciones de Victoria y Empate", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(
                            text = "- Ganas si: El crupier se pasa del límite, o si al final del turno tu mano es mayor que la del crupier sin pasarte.\n- Pierdes si: Te pasas del límite (Busto), o si la mano del crupier es mayor que la tuya sin pasarse.\n- Empate (Push): Si ambos terminan con exactamente la misma cantidad de puntos, nadie gana y se registra como empate.",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        Button(
                            onClick = { finish() },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text("Regresar")
                        }
                    }
                }
            }
        }
    }
}
