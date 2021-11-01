package com.example.primeiroapp.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.parseAsHtml
import com.example.primeiroapp.R
import com.example.primeiroapp.model.Usuario
import com.example.primeiroapp.utils.convertStringToLocalDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class NovoUsuarioActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var editDataNascimento: EditText
    lateinit var radioF : RadioButton
    lateinit var radioM : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        editEmail = findViewById(R.id.et_email)
        editSenha = findViewById(R.id.et_senha)
        editNome = findViewById(R.id.et_nome)
        editProfissao = findViewById(R.id.et_profissao)
        editAltura = findViewById(R.id.et_altura)
        editDataNascimento = findViewById(R.id.et_data)
        radioF = findViewById(R.id.radio_feminino)
        radioM = findViewById(R.id.radio_masculino)

        supportActionBar!!.title = "Perfil"

        //Criar um calendário
        val calendario = Calendar.getInstance()

        //Determinar os dados (dia, mês e ano) do calendário
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //Abrir o componente DatePicker
        val etDataNascimento = findViewById<EditText>(R.id.et_data)

        etDataNascimento.setOnClickListener {
            val dp = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, _ano, _mes, _dia ->

                    var diaFinal = _dia
                    var mesFinal = _mes + 1

                    var mesString = "$mesFinal"
                    var diaString = "$diaFinal"

                    if (mesFinal < 10){
                        mesString = "0$mesFinal"
                    }

                    if (diaFinal < 10){
                        diaString = "0$diaFinal"
                    }

                    etDataNascimento.setText("$diaString/$mesString/$_ano")

                },
                ano,
                mes,
                dia
            )

            dp.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_salvar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (validarCampos()) {

            //Criar o objeto Usuário

                val nascimento = convertStringToLocalDate(editDataNascimento.text.toString())

            val usuario = Usuario(
                1,
                editNome.text.toString(),
                editEmail.text.toString(),
                editSenha.text.toString(),
                0,
                editAltura.text.toString().toDouble(),
                LocalDate.of(
                    nascimento.year,
                    nascimento.month,
                    nascimento.dayOfMonth
                ),
                editProfissao.text.toString(),
                if (radioF.isChecked) 'F' else 'M'
            )

            //Salvar o registro em um SharedPreferences

            //A instrução abaixo cria um arquivo sharedPreferences caso não exista. Se existir, será aberto para edição

            val dados = getSharedPreferences("usuario", Context.MODE_PRIVATE)

            val editor = dados.edit()
            editor.putInt("id", usuario.id)
            editor.putString("nome", usuario.nome)
            editor.putString("email", usuario.email)
            editor.putString("senha", usuario.senha)
            editor.putInt("peso", usuario.peso)
            editor.putFloat("altura", usuario.altura.toFloat())
            editor.putString("dataNascimento", usuario.dataNascimento.toString())
            editor.putString("profissao", usuario.profissao)
            editor.putString("sexo", usuario.sexo.toString())
            editor.apply()

        }

        Toast.makeText(this, "Usuário cadastrado", Toast.LENGTH_SHORT).show()

        return true

    }

    fun validarCampos(): Boolean {

        var valido = true

        if (editEmail.text.isEmpty()) {
            editEmail.error = "Email é um campo obrigatório"
            valido = false
        }

        if (editSenha.text.isEmpty()) {
            editSenha.error = "Senha é um campo obrigatório"
            valido = false
        }

        if (editNome.text.isEmpty()) {
            editNome.error = "Nome é um campo obrigatório"
            valido = false
        }

        if (editProfissao.text.isEmpty()) {
            editProfissao.error = "Profissão é um campo obrigatório"
            valido = false
        }

        if (editAltura.text.isEmpty()) {
            editAltura.error = "Altura é um campo obrigatório"
            valido = false
        }

        if (editDataNascimento.text.isEmpty()) {
            editDataNascimento.error = "Data de nascimento é um campo obrigatório"
            valido = false
        }

//        if (editSexo.getCheckedRadioButtonId() == -1)
//        {
//
//        }

        return valido

    }

}




