package com.example.primeiroapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.primeiroapp.R
import com.example.primeiroapp.utils.convertBase64ToBitMap

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar!!.hide()

//        val arquivo = applicationContext.getSharedPreferences("usuario", Context.MODE_PRIVATE)
        val prefs = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        val tvNome = findViewById<TextView>(R.id.tv_nome)
        val tvProfissao = findViewById<TextView>(R.id.tv_profissao)
        val tvAltura = findViewById<TextView>(R.id.tv_altura)
        val tvDataNascimento = findViewById<TextView>(R.id.tv_idade)
        val tvImc = findViewById<TextView>(R.id.tv_imc)
        val tvNcd = findViewById<TextView>(R.id.tv_ncd)
        val tvPeso = findViewById<TextView>(R.id.tv_peso)
        val tvFotoPerfil = findViewById<ImageView>(R.id.iv_dash_foto)


        //Nome
        val name = prefs.getString("nome", "")
        tvNome.setText(name.toString())

        //Profissão
        val profissao = prefs.getString("profissao", "")
        tvProfissao.setText(profissao.toString())

        //Altura
        val altura = prefs.getFloat("altura", 0.00F)
        tvAltura.setText(altura.toString())

        //Idade
//        val idade = prefs.getString("dataNascimento", "")
//        tvDataNascimento.setText(idade.toString())

        val bitmap = convertBase64ToBitMap(prefs.getString("fotoPerfil", ""))!!
        tvFotoPerfil.setImageBitmap(bitmap)


    }
}