package com.example.primeiroapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.primeiroapp.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class PesagemActivity : AppCompatActivity() {

    lateinit var tvDataAtual : TextView

    lateinit var etPeso : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesagem)

        tvDataAtual = findViewById(R.id.tv_data_atual)

        etPeso = findViewById(R.id.edit_text_peso)

        val date = Calendar.getInstance().time

        var dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        tvDataAtual.text = dateTimeFormat.format(date)

        var buttonSalvarPeso = findViewById<Button>(R.id.button_salvar_peso)

        buttonSalvarPeso.setOnClickListener {
            Toast.makeText(this, "Peso cadastrado", Toast.LENGTH_SHORT).show()
        }


        val spinner: Spinner = findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


    }
}