package com.malong.flutterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malong.flutterapp.ui.theme.FlutterappTheme
import kotlin.text.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlutterappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TemperatureConverter(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TemperatureConverter(modifier: Modifier = Modifier) {
    var celsius by remember { mutableStateOf("") }
    var fahrenheit by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Temperature Converter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = celsius,
            onValueChange = {
                celsius = it
                fahrenheit = if (it.isNotEmpty()) {
                    String.format("%.2f", (it.toDoubleOrNull() ?: 0.0) * 9/5 + 32)
                } else {
                    ""
                }
            },
            label = { Text("Celsius") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = fahrenheit,
            onValueChange = {
                fahrenheit = it
                celsius = if (it.isNotEmpty()) {
                    String.format("%.2f", ((it.toDoubleOrNull() ?: 0.0) - 32) * 5/9)
                } else {
                    ""
                }
            },
            label = { Text("Fahrenheit") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TemperatureConverterPreview() {
    FlutterappTheme {
        TemperatureConverter()
    }
}