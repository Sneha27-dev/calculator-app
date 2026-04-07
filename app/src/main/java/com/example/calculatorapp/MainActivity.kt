package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp()
        }
    }
}
@Composable
fun CalculatorApp() {
    var amount by remember { mutableStateOf("") }
    var percentage by remember { mutableStateOf(10f) }
    var roundUp by remember { mutableStateOf(false) }
    val numericAmount = amount.toDoubleOrNull()
    val result = if (numericAmount != null) {
        var tip = (numericAmount * percentage) / 100
        if (roundUp) tip = ceil(tip)
        tip
    } else 0.0
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Enter Amount") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tip Percentage: ${percentage.toInt()}%")
        Slider(
            value = percentage,
            onValueChange = { percentage = it },
            valueRange = 0f..30f
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text("Round Up")
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Calculated Tip:%.2f".format(result))
    }
}