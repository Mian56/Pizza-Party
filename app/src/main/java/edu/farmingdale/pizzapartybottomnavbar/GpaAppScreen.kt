package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ToDo 4: Match the UI as in drawable gpa_design.png. Use the following hints:
// - The background color should be Color.Cyan
// - Fix padding, alignment, and keypad type

// ToDo 5:  Add the GpaAppScreen composable button that clears the input fields when clicked


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GpaAppScreen() {

    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }


    // Declare variables for GPA result and background color
    var gpa by remember { mutableStateOf("") }
    var backColor by remember { mutableStateOf(Color.Cyan) } //color set to cyan
    var btnLabel by remember { mutableStateOf("Calulate GPA") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backColor) //background color
            .padding(16.dp), //padding
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = grade1,
            onValueChange = { grade1 = it },
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .border(1.dp,Color.DarkGray,shape = RoundedCornerShape(12.dp)),
            colors =  TextFieldDefaults.textFieldColors(
                containerColor = Color.Cyan, // Cyan background inside the TextField
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            label = { Text("Course 1 Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) //Set KeyboardType to Number

        )

        TextField(
            value = grade2,
            onValueChange = { grade2 = it },
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .border(1.dp,Color.DarkGray,shape = RoundedCornerShape(12.dp)),
            colors =  TextFieldDefaults.textFieldColors(
                containerColor = Color.Cyan, // Cyan background inside the TextField
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            label = { Text("Course 2 Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) //Set KeyboardType to Number

        )



        TextField(
            value = grade3,
            onValueChange = { grade3 = it },
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .border(1.dp,Color.DarkGray,shape = RoundedCornerShape(12.dp)),
            colors =  TextFieldDefaults.textFieldColors(
                containerColor = Color.Cyan, // Cyan background inside the TextField
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            label = { Text("Course 3 Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) //Set KeyboardType to Number

        )

        //

        Button(onClick = {
            if (btnLabel == "Compute GPA") {

                val gpaVal = calGPA(grade1, grade2, grade3)
                if (gpaVal != null) {
                    gpa = gpaVal.toString()

                    // Change background color based on GPA
                    backColor = when {
                        gpaVal < 60 -> Color.Red
                        gpaVal in 60.0..79.0 -> Color.Yellow
                        else -> Color.Green
                    }
                    btnLabel = "Clear"
                } else {
                    gpa = "Invalid input"
                }
            } else {
                // Reset all value to none
                grade1 = ""
                grade2 = ""
                grade3 = ""
                gpa = ""
                backColor = Color.White
                btnLabel = "Compute GPA"
            }
        }, modifier = Modifier.padding(top = 56.dp)) {
            Text(btnLabel)
        }


        if (gpa.isNotEmpty()) {
            Text(text = "GPA: $gpa")
        }

        // Display GPA result
        if (gpa.isNotEmpty()) {
            Text(
                text = "GPA: $gpa",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

// Function to calculate GPA
fun calGPA(grade1: String, grade2: String, grade3: String): Double? {
    return try {
        val grades = listOf(grade1.toDouble(), grade2.toDouble(), grade3.toDouble())
        grades.average()
    } catch (e: NumberFormatException) {
        null
    }
}