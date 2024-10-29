package com.example.simplelist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listViewResults)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()
            val n = input.toIntOrNull()

            if (n == null || n <= 0) {
                textViewError.text = "Please enter a positive integer"
                listView.adapter = null
            } else {
                textViewError.text = ""
                val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                val numbers = when (selectedRadioButtonId) {
                    R.id.radioEven -> (0..n).filter { it % 2 == 0 }
                    R.id.radioOdd -> (0..n).filter { it % 2 != 0 }
                    R.id.radioPerfectSquare -> (0..n).filter { Math.sqrt(it.toDouble()).toInt() * Math.sqrt(it.toDouble()).toInt() == it }
                    else -> (0..n).toList()
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
                listView.adapter = adapter
            }
        }
    }

}