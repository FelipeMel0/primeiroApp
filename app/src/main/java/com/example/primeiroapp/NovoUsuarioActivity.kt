package com.example.primeiroapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class NovoUsuarioActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var editDataNascimento: EditText
    lateinit var editSexo: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        editEmail = findViewById(R.id.et_email)
        editSenha = findViewById(R.id.et_senha)
        editNome = findViewById(R.id.et_nome)
        editProfissao = findViewById(R.id.et_profissao)
        editAltura = findViewById(R.id.et_altura)
        editDataNascimento = findViewById(R.id.et_data)
//        editSexo = findViewById(R.id.rg_sexo)


        supportActionBar!!.title = "Perfil"

        //Criar um calendário
        val calendario = Calendar.getInstance()

        //Determinar os dados (dia, mês e ano) do calendário
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //Abrir o componente DatePicker
        val etDataNascimento = findViewById<EditText>(R.id.et_data)

        etDataNascimento.setOnClickListener{
            val dp = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->
                etDataNascimento.setText("$_dia/${_mes + 1}/$_ano")
            }, ano, mes, dia)

            dp.show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_salvar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (validarCampos()){
            //Salvar o registro
        }

//        when (item.itemId){

//            R.id.icone_salvar -> {
//            Toast.makeText(this, "Salvar", Toast.LENGTH_SHORT).show()
//            }

//        }

        return true

    }

    fun validarCampos() : Boolean{

        var valido = true

        if (editEmail.text.isEmpty()){
            editEmail.error = "Email é um campo obrigatório"
            valido = false
        }

        if (editSenha.text.isEmpty()){
            editSenha.error = "Senha é um campo obrigatório"
            valido = false
        }

        if (editNome.text.isEmpty()){
            editNome.error = "Nome é um campo obrigatório"
            valido = false
        }

        if (editProfissao.text.isEmpty()){
            editProfissao.error = "Profissão é um campo obrigatório"
            valido = false
        }

        if (editAltura.text.isEmpty()){
            editAltura.error = "Altura é um campo obrigatório"
            valido = false
        }

        if(editDataNascimento.text.isEmpty()){
            editDataNascimento.error = "Data de nascimento é um campo obrigatório"
            valido = false
        }

//        if(editSexo.text.isEmpty()){
//            editSexo.error = "Sexo é um campo obrigatório"
//            valido = false
//        }

        return valido

    }





}




