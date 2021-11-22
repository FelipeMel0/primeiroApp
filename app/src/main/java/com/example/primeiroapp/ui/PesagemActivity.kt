package com.example.primeiroapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.primeiroapp.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class PesagemActivity : AppCompatActivity() {

    lateinit var tvDataAtual : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesagem)

        tvDataAtual = findViewById(R.id.tv_data_atual)

        val date = Calendar.getInstance().time

        var dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        tvDataAtual.text = dateTimeFormat.format(date)

        var buttonSalvarPeso = findViewById<Button>(R.id.button_salvar_peso)

        buttonSalvarPeso.setOnClickListener {
            Toast.makeText(this, "Peso cadastrado", Toast.LENGTH_SHORT).show()
        }
    }
}