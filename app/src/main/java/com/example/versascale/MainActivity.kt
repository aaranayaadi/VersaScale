//Unit converter application
package com.example.versascale

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.geometry.times
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.versascale.ui.theme.VersaScaleTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.format.TextStyle
import kotlin.math.roundToInt
import kotlin.time.times
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VersaScaleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VersaScale()
                }
            }
        }
    }
}


@Composable
fun VersaScale(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputMenuExpanded by remember { mutableStateOf(false) }
    var outputMenuExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 50.sp,
        color = Color.Red
    )

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        // "?:" is the elvis operator
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0/oConversionFactor.doubleValue).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        //Here all UI elements will be stacked below each other

        Text(text = "VersaScale", style = customTextStyle)
        Text(text = "The Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue = it
            /*Here is what should happen when the value of the
            OutlinedTextField changes */
        },
            label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row { //Here all UI elements will be stacked besides each other
            //A box is a layout element just like a row and column.
            //It works differently in that it arranges and stacks
            //composables on top of each other. It allows dropdown menu.
            Box {
                //Input button
                Button(onClick = { inputMenuExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = inputMenuExpanded, onDismissRequest = { inputMenuExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        inputMenuExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.doubleValue = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        inputMenuExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.doubleValue = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        inputMenuExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.doubleValue = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        inputMenuExpanded = false
                        inputUnit = "Millimeters"
                        conversionFactor.doubleValue = 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp), )
            Icon(Icons.Default.ArrowForward, contentDescription = "Arrow Forward", Modifier.padding(0.dp, 13.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                //output button
                Button(onClick = { outputMenuExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = outputMenuExpanded, onDismissRequest = { outputMenuExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        outputMenuExpanded = false
                        outputUnit = "Centimeters"
                        oConversionFactor.doubleValue = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        outputMenuExpanded = false
                        outputUnit = "Meters"
                        oConversionFactor.doubleValue = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        outputMenuExpanded = false
                        outputUnit = "Feet"
                        oConversionFactor.doubleValue = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        outputMenuExpanded = false
                        outputUnit = "Millimeters"
                        oConversionFactor.doubleValue = 0.001
                        convertUnits()
                    })
                }
            }
            /*val context = LocalContext.current
            Button(onClick = { Toast.makeText(context, "Thanks for clicking", Toast.LENGTH_LONG).show() }) {
                Text(text = "Click Me!")*/
            //above is the syntax for a button with toast

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $outputUnit", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun VersaScalePreview(){
    VersaScale()
}