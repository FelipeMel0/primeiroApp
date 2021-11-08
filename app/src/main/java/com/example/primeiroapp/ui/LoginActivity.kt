package com.example.primeiroapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import com.example.primeiroapp.R
import com.example.primeiroapp.utils.autenticar


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()

        val tvCriarConta = findViewById<TextView>(R.id.criar_conta)
        val buttonEntrar = findViewById<Button>(R.id.button_entrar)

        val editEmail = findViewById<EditText>(R.id.edit_login_email)
        val editSenha = findViewById<EditText>(R.id.edit_login_senha)

        buttonEntrar.setOnClickListener {
            val autenticou = autenticar(editEmail.text.toString(), editSenha.text.toString(), this)

            if (autenticou) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            } else{
                android.widget.Toast.makeText(this, "Algo deu errado", Toast.LENGTH_SHORT).show()
            }
        }

        tvCriarConta.setOnClickListener {
            val abrirDatePickerActivity = Intent(this, NovoUsuarioActivity::class.java)

            startActivity(abrirDatePickerActivity)
        }

    }
}