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

    lateinit var spinnerNivel : Spinner

    lateinit var btnRegistrarPeso : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesagem)

        tvDataAtual = findViewById(R.id.tv_data_atual)

        etPeso = findViewById(R.id.edit_text_peso)

        //Formatando a data
        val date = Calendar.getInstance().time

        var dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        tvDataAtual.text = dateTimeFormat.format(date)



        spinnerNivel = findViewById(R.id.spinner_niveis)

        btnRegistrarPeso = findViewById(R.id.button_salvar_peso)

        btnRegistrarPeso.setOnClickListener {
            val arquivo = getSharedPreferences("usuario", MODE_PRIVATE)
            val editor = arquivo.edit()
            editor.putString("pesagem", etPeso.text.toString())
            editor.putString("data_pesagem", LocalDate.now().toString())
            editor.putString("nivel", spinnerNivel.selectedItemPosition.toString())
            editor.apply()
        }

    }
}