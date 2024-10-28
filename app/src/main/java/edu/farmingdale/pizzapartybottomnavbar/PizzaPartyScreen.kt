package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil
import androidx.lifecycle.ViewModel // Import ViewModel

// ToDo 6: Add another level of hunger that is Hungry that is in between Medium and Very hungry
val hungerLevels = listOf("Light", "Medium", "Hungry", "Very hungry") // Added Hungry

// ToDo 7: Using the ViewModel class, create a new ViewModel class called PizzaPartyViewModel as
class PizzaPartyViewModel : ViewModel() {
    var totalPizzas by mutableIntStateOf(0)
    var numPeopleInput by mutableStateOf("")
    var hungerLevel by mutableStateOf("Medium")

    fun calculatePizzas() {
        totalPizzas = calculateNumPizzas(numPeopleInput.toInt(), hungerLevel)
    }
}//task 7 completed with task 6 forgot to commit

@Composable
fun PizzaPartyScreen(modifier: Modifier = Modifier, viewModel: PizzaPartyViewModel = remember { PizzaPartyViewModel() }) {
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        Text(
            text = "Pizza Party",
            fontSize = 38.sp,
            modifier = modifier.padding(bottom = 16.dp)
        )
        NumberField(
            labelText = "Number of people?",
            textInput = viewModel.numPeopleInput,
            onValueChange = { viewModel.numPeopleInput = it },
            modifier = modifier.padding(bottom = 16.dp).fillMaxWidth()
        )
        RadioGroup(
            labelText = "How hungry?",
            radioOptions = hungerLevels, // Use updated hungerLevels
            selectedOption = viewModel.hungerLevel,
            onSelected = { viewModel.hungerLevel = it },
            modifier = modifier
        )
        Text(
            text = "Total pizzas: ${viewModel.totalPizzas}",
            fontSize = 22.sp,
            modifier = modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Button(
            onClick = { viewModel.calculatePizzas() }, // Call ViewModel method to calculate pizzas
            modifier = modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }
    }
}

@Composable
fun NumberField(
    labelText: String,
    textInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textInput,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
    )
}

@Composable
fun RadioGroup(
    labelText: String,
    radioOptions: List<String>,
    selectedOption: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isSelectedOption: (String) -> Boolean = { selectedOption == it }

    Column {
        Text(labelText)
        radioOptions.forEach { option ->
            Row(
                modifier = modifier
                    .selectable(
                        selected = isSelectedOption(option),
                        onClick = { onSelected(option) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedOption(option),
                    onClick = null,
                    modifier = modifier.padding(end = 8.dp)
                )
                Text(
                    text = option,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}

fun calculateNumPizzas(
    numPeople: Int,
    hungerLevel: String
): Int {
    val slicesPerPizza = 8
    val slicesPerPerson = when (hungerLevel) {
        "Light" -> 2
        "Medium" -> 3
        "Hungry" -> 4 // Added slices for "Hungry" level
        else -> 5
    }

    return ceil(numPeople * slicesPerPerson / slicesPerPizza.toDouble()).toInt()
}
