package com.example.blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.blackjack.ui.theme.BlackjackTheme

class JuegoActivity : ComponentActivity() {

    private val viewModel: JuegoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Recuperar los extras del Intent
        val nombre = intent.getStringExtra("nombre") ?: "Jugador"
        val meta = intent.getIntExtra("meta", 21)

        viewModel.iniciarPartida(meta)

        setContent {
            BlackjackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaJuego(
                        viewModel = viewModel,
                        nombre = nombre,
                        meta = meta,
                        onRegresar = { finish() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaJuego(
    viewModel: JuegoViewModel,
    nombre: String,
    meta: Int,
    onRegresar: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Recolectar el estado
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Crupier
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val tituloCrupier = if (uiState.estadoPartida == "Jugando") {
                "Crupier"
            } else {
                "Crupier (Puntos: ${uiState.puntosCrupier})"
            }
            Text(
                text = tituloCrupier,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(uiState.manoCrupier) { index, carta ->
                    val isOculta = uiState.estadoPartida == "Jugando" && index == 0
                    CartaView(carta = carta, isOculta = isOculta)
                }
            }
        }

        // Estado
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Meta: $meta | Estado: ${uiState.estadoPartida}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }

        // Jugador y Acciones
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$nombre (Puntos: ${uiState.puntosJugador})",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                items(uiState.manoJugador) { carta ->
                    CartaView(carta = carta, isOculta = false)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.pedirCartaJugador() },
                    enabled = uiState.estadoPartida == "Jugando"
                ) {
                    Text("Pedir Carta")
                }
                
                OutlinedButton(
                    onClick = { viewModel.plantarse() },
                    enabled = uiState.estadoPartida == "Jugando"
                ) {
                    Text("Plantarse")
                }
            }

            TextButton(onClick = onRegresar) {
                Text("Regresar")
            }
        }
    }
}

// Componente CartaView
@Composable
fun CartaView(carta: Carta, isOculta: Boolean) {
    Card(
        modifier = Modifier
            .width(80.dp)
            .height(120.dp)
    ) {
        val idImagen = if (isOculta) R.drawable.black_playing_card_back_25478_512 else carta.idImagen
        Image(
            painter = painterResource(id = idImagen),
            contentDescription = if (isOculta) "Carta Oculta" else carta.nombre,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}