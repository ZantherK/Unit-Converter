package com.example.unitconvert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvert.ui.theme.UnitConvertTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpended by remember { mutableStateOf(false)}
    var oExpended by remember { mutableStateOf(false)}
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oconversionFactor = remember { mutableDoubleStateOf(1.00)}

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble*conversionFactor.doubleValue*100.0/oconversionFactor.doubleValue).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = {Text("Enter Value")}
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Box{
                Button(onClick = { iExpended = true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpended, onDismissRequest = { iExpended = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            iExpended = false
                            inputUnit = "Centimeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            iExpended = false
                            inputUnit = "Meters"
                            conversionFactor.doubleValue = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            iExpended = false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters")},
                        onClick = {
                            iExpended = false
                            inputUnit = "Millimeters"
                            conversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Box{
                Button(onClick = { oExpended = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpended, onDismissRequest = { oExpended = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            oExpended = false
                            outputUnit = "Centimeters"
                            oconversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            oExpended = false
                            outputUnit = "Meters"
                            oconversionFactor.doubleValue = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            oExpended = false
                            outputUnit = "Feet"
                            oconversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters")},
                        onClick = {
                            oExpended = false
                            outputUnit = "Millimeters"
                            oconversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Result: $outputValue $outputUnit", style = MaterialTheme.typography.headlineSmall)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}