package com.example.primeiroapp.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.text.parseAsHtml
import androidx.core.view.drawToBitmap
import com.example.primeiroapp.R
import com.example.primeiroapp.model.Usuario
import com.example.primeiroapp.utils.convertStringToLocalDate
import com.example.primeiroapp.utils.encodeImage
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

const val CODE_IMAGE = 5000

class NovoUsuarioActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var editNome: EditText
    lateinit var editProfissao: EditText
    lateinit var editAltura: EditText
    lateinit var editDataNascimento: EditText
    lateinit var radioF : RadioButton
    lateinit var radioM : RadioButton
    lateinit var tvTrocarFoto : TextView
    lateinit var ivFotoPerfil : ImageView

    var imagemBitmap : Bitmap? = null

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
        tvTrocarFoto = findViewById(R.id.tv_trocar_foto)
        ivFotoPerfil = findViewById(R.id.iv_foto_perfil)

//        imagemBitmap = resources.getDrawable(R.drawable.pessoaconfusa).toBitmap()

        imagemBitmap = BitmapFactory.decodeResource(resources, R.drawable.pessoaconfusa)

        supportActionBar!!.title = "Perfil"

        //Abrir a galeria de fotos
        tvTrocarFoto.setOnClickListener {
            abrirGaleria()
        }

        //Criar um calend??rio
        val calendario = Calendar.getInstance()

        //Determinar os dados (dia, m??s e ano) do calend??rio
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, imagem: Intent?) {
        super.onActivityResult(requestCode, resultCode, imagem)

        if (requestCode == CODE_IMAGE && resultCode == -1){
            //Recuperar a imagem do stream
            val fluxoImagem = contentResolver.openInputStream(imagem!!.data!!)

            //Converter os bits em um bitmap
            imagemBitmap = BitmapFactory.decodeStream(fluxoImagem)

            //Colocar o bitmap no imageview
            ivFotoPerfil.setImageBitmap(imagemBitmap)
        }

    }

    private fun abrirGaleria() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"

        //Abrir a activity respons??vel por abrir as imagens
        //Essa activity retornar?? o conte??do selecionado para o app
        startActivityForResult(Intent.createChooser(intent, "Escolha uma foto"), CODE_IMAGE)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_salvar, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (validarCampos()) {

            //Criar o objeto Usu??rio
                val nascimento = convertStringToLocalDate(editDataNascimento.text.toString())

            val perfil = encodeImage(ivFotoPerfil.drawToBitmap())

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
                if (radioF.isChecked) 'F' else 'M',
                perfil.toString()

            )

            //Salvar o registro em um SharedPreferences

            //A instru????o abaixo cria um arquivo sharedPreferences caso n??o exista. Se existir, ser?? aberto para edi????o

            val dados = getSharedPreferences("usuario", MODE_PRIVATE)

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
            editor.putString("fotoPerfil", usuario.fotoPerfil)
            editor.apply()

        }

        Toast.makeText(this, "Usu??rio cadastrado", Toast.LENGTH_SHORT).show()

        return true

    }

    fun validarCampos(): Boolean {

        var valido = true

        if(imagemBitmap == null){

        }

        if (editEmail.text.isEmpty()) {
            editEmail.error = "Email ?? um campo obrigat??rio"
            valido = false
        }

        if (editSenha.text.isEmpty()) {
            editSenha.error = "Senha ?? um campo obrigat??rio"
            valido = false
        }

        if (editNome.text.isEmpty()) {
            editNome.error = "Nome ?? um campo obrigat??rio"
            valido = false
        }

        if (editProfissao.text.isEmpty()) {
            editProfissao.error = "Profiss??o ?? um campo obrigat??rio"
            valido = false
        }

        if (editAltura.text.isEmpty()) {
            editAltura.error = "Altura ?? um campo obrigat??rio"
            valido = false
        }

        if (editDataNascimento.text.isEmpty()) {
            editDataNascimento.error = "Data de nascimento ?? um campo obrigat??rio"
            valido = false
        }

//        if (editSexo.getCheckedRadioButtonId() == -1)
//        {
//
//        }

        return valido

    }

}




