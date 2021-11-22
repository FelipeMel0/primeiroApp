package com.example.primeiroapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.primeiroapp.R
import com.example.primeiroapp.utils.calcularIdade
import com.example.primeiroapp.utils.convertBase64ToBitMap

class DashboardActivity : AppCompatActivity() {

//    val tvNome = findViewById<TextView>(R.id.tv_nome)
//    val tvProfissao = findViewById<TextView>(R.id.tv_profissao)
//    val tvAltura = findViewById<TextView>(R.id.tv_altura)
//    val tvDataNascimento = findViewById<TextView>(R.id.tv_idade)
//    val tvImc = findViewById<TextView>(R.id.tv_imc)
//    val tvNcd = findViewById<TextView>(R.id.tv_ncd)
//    val tvPeso = findViewById<TextView>(R.id.tv_peso)
//    val tvIdade = findViewById<TextView>(R.id.tv_idade)
//    val tvFotoPerfil = findViewById<ImageView>(R.id.iv_dash_foto)

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
//
//    tvFotoPerfil = findViewById(R.id.iv_dash_foto)



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

//        val tvPesar = findViewById<TextView>(R.id.ll_pesar_agora)

        val tvPesar = findViewById<LinearLayout>(R.id.ll_pesar_agora)

        carregarDashboard()

        tvPesar.setOnClickListener {
            val abrirDatePickerActivity = Intent(this, PesagemActivity::class.java)

            startActivity(abrirDatePickerActivity)
        }

    }

    private fun carregarDashboard() {
        val arquivo =
            getSharedPreferences(
                "usuario", MODE_PRIVATE)

        tvNome.text = arquivo.getString("nome", "")
        tvProfissao.text = arquivo.getString("profissao", "")
        tvAltura.text = arquivo.getFloat("altura", 0.0f).toString()

        tvIdade.text = calcularIdade(arquivo.getString("dataNascimento", "")!!).toString()

        val bitmap = convertBase64ToBitMap(arquivo.getString("fotoPerfil", "")!!)
        tvFotoPerfil.setImageBitmap(bitmap)

    }

}