package com.example.assignment1guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    // Declaring variables for the UI elements
    private lateinit var ageEditText: EditText
    private lateinit var matchButton: Button
    private lateinit var clearButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var exitButton: Button
    private var backPressed : Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        ageEditText = findViewById(R.id.ageTxt)
        matchButton = findViewById(R.id.matchBtn)
        clearButton = findViewById(R.id.clearBtn)
        resultTextView = findViewById(R.id.resultTxt)
        exitButton = findViewById(R.id.exitBtn)

        // Set click listeners for buttons
        matchButton.setOnClickListener {
            handleMatchButtonClick()
        }

        clearButton.setOnClickListener {
            handleClearButtonClick()
        }

        exitButton.setOnClickListener {
            handleExitButtonClick()
        }

    }

    // Function to handle click on Match button
    private fun handleMatchButtonClick() {

        val ageInput = ageEditText.text.toString()
        val age = ageInput.toIntOrNull()

        // handling for when wrong values inputted into edittext
        resultTextView.text = when {
            ageInput.isEmpty() -> "You to enter your age"
            age == null -> "Invalid input. Please enter a valid whole number"
            age !in 20..100 -> "Input out of range. Age must be between 20 and 100"
            // if all above inapplicable, continue to the matchAgeToHistoricalFigure function below
            else -> matchAgeToHistoricalFigure(age)
        }
    }

    // Function to match given age to a historical figure
    private fun matchAgeToHistoricalFigure(age: Int): String {
        // Map of historical figures and their ages at death
        val historicalFigures = mapOf(
            "Jeanne Calment" to 122,
            "Leaonardo da Vinci" to 67,
            "Mozart" to 35,
        )

        // Iterate through historical figures to find match
        for ((person, personAge) in historicalFigures) {
            if ( age == personAge) {
                return "You share the same age with $person when they passed away"
            }
        }

        return "No historical figure found with the same age at death."
    }

    // Function to handle click on Clear button
    private fun handleClearButtonClick() {
        // Clear age input and result text
        ageEditText.text.clear()
        resultTextView.text = ""
    }

    // Function to handle click on Exit button
    private fun handleExitButtonClick() {
        moveTaskToBack(true)
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(1)
    }

    override fun onBackPressed() {
        // Handle back button press to exit application
        if (backPressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }
        else{
            Toast.makeText(this, "Press again to exit?", Toast.LENGTH_SHORT).show()
        }
        backPressed = System.currentTimeMillis()
    }

}