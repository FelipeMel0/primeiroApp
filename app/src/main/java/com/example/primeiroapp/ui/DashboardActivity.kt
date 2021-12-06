package com.example.primeiroapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.primeiroapp.R
import com.example.primeiroapp.respository.PesagemRepository
import com.example.primeiroapp.utils.calcularIdade
import com.example.primeiroapp.utils.convertBase64ToBitMap

class DashboardActivity : AppCompatActivity() {

    lateinit var tvNome: TextView
    lateinit var tvProfissao: TextView
    lateinit var tvImc: TextView
    lateinit var tvNcd: TextView
    lateinit var tvPeso: TextView
    lateinit var tvIdade: TextView
    lateinit var tvAltura: TextView
    lateinit var ivPerfil: ImageView
    lateinit var tvDataNascimento : TextView
    lateinit var tvFotoPerfil: ImageView
    lateinit var cardHistorico: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar!!.hide()

        tvNome = findViewById(R.id.tv_nome)
        tvProfissao = findViewById(R.id.tv_profissao)
        tvAltura = findViewById(R.id.tv_altura)
        tvDataNascimento = findViewById(R.id.tv_idade)
        tvImc = findViewById(R.id.tv_imc)
        tvNcd = findViewById(R.id.tv_ncd)
        tvPeso = findViewById(R.id.tv_peso)
        tvIdade = findViewById(R.id.tv_idade)
        tvFotoPerfil = findViewById(R.id.iv_dash_foto)
        cardHistorico = findViewById(R.id.card_historico)

        val tvPesar = findViewById<LinearLayout>(R.id.ll_pesar_agora)

        carregarDashboard()

        tvPesar.setOnClickListener {
            val abrirDatePickerActivity = Intent(this, PesagemActivity::class.java)

            startActivity(abrirDatePickerActivity)
        }

        cardHistorico.setOnClickListener{

            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)

        }

    }

    private fun carregarDashboard() {
        val arquivo = getSharedPreferences("usuario", MODE_PRIVATE)

        tvNome.text = arquivo.getString("nome", "")
        tvProfissao.text = arquivo.getString("profissao", "")
        tvAltura.text = arquivo.getFloat("altura", 0.0f).toString()

        tvIdade.text = calcularIdade(arquivo.getString("dataNascimento", "")!!).toString()

        val bitmap = convertBase64ToBitMap(arquivo.getString("fotoPerfil", "")!!)
        tvFotoPerfil.setImageBitmap(bitmap)

    }

}