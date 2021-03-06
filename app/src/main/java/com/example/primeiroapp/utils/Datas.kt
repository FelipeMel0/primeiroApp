package com.example.primeiroapp.utils

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun convertStringToLocalDate(brazilDate: String) : LocalDate{
    val dateFormatterFromBrazil = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val localDateFormat = LocalDate.parse(brazilDate, dateFormatterFromBrazil)

    return localDateFormat

}

fun calcularIdade(dataNascimento: String) : Int {

    val hoje = LocalDate.now()

    //Converter a data de nascimento em um LocalDate
    //Extrair a data em um array

    val nascimentoArray = dataNascimento.split("-").toTypedArray()

    val nascimento = LocalDate.of(nascimentoArray[0].toInt(), nascimentoArray[1].toInt(), nascimentoArray[2].toInt())

    val idade = Period.between(nascimento, hoje). years

    return idade
}
