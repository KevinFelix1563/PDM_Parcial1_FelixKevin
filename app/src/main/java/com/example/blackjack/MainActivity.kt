package com.example.blackjack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.blackjack.ui.theme.TemaBlackjack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var temaGlobal by remember { mutableStateOf("Verde") }
            
            TemaBlackjack(temaSeleccionado = temaGlobal) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MenuPrincipal(
                        temaActual = temaGlobal,
                        onTemaChange = { temaGlobal = it },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MenuPrincipal(
    temaActual: String,
    onTemaChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var meta by remember { mutableFloatStateOf(21f) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Blackjack",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Jugador") },
            placeholder = { Text("Escribe tu nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Meta: ${meta.toInt()}",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Slider(
            value = meta,
            onValueChange = { meta = it },
            valueRange = 11f..25f,
            steps = 13,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Tema",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = { onTemaChange("Verde") },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (temaActual == "Verde") MaterialTheme.colorScheme.primaryContainer else Color.Transparent
                )
            ) {
                Text("Verde")
            }
            OutlinedButton(
                onClick = { onTemaChange("Oscuro") },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (temaActual == "Oscuro") MaterialTheme.colorScheme.primaryContainer else Color.Transparent
                )
            ) {
                Text("Oscuro")
            }
            OutlinedButton(
                onClick = { onTemaChange("Rojo") },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (temaActual == "Rojo") MaterialTheme.colorScheme.primaryContainer else Color.Transparent
                )
            ) {
                Text("Rojo")
            }
        }

        Button(
            onClick = {
                val intent = Intent(context, JuegoActivity::class.java).apply {
                    putExtra("nombre", nombre)
                    putExtra("meta", meta.toInt())
                    putExtra("tema", temaActual)
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            enabled = nombre.isNotBlank()
        ) {
            Text("Jugar")
        }

        Button(
            onClick = {
                val intent = Intent(context, ScoreActivity::class.java).apply {
                    putExtra("tema", temaActual)
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Ver Score")
        }

        Button(
            onClick = {
                val intent = Intent(context, InstruccionesActivity::class.java).apply {
                    putExtra("tema", temaActual)
                }
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Instrucciones")
        }
    }
}
