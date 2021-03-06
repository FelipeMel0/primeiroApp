package com.example.primeiroapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import android.util.Base64
import java.util.*


//fun encodeImage(bm: Bitmap): String? {
//    val baos = ByteArrayOutputStream()
//    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//    val b = baos.toByteArray()
////    Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
//    return Base64.getEncoder().encodeToString(b)
//}

fun encodeImage(bitmap: Bitmap) : String {
    val bitmapArray = ByteArrayOutputStream()

    bitmap.compress(
        Bitmap.CompressFormat.JPEG,
        100,
        bitmapArray)

    return Base64.encodeToString(
        bitmapArray.toByteArray(),
        Base64.DEFAULT)
}

//fun encodeToBitmap(imagem64: String) : String? {
//    val decodedString: Base64.Decoder? = Base64.getDecoder(imagem64)
//    val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
//}

fun convertBase64ToBitMap(base64Image: String) : Bitmap{

    //Converter Base64 em bytes

    val imageBytes = Base64.decode(base64Image, Base64.DEFAULT)

    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

}
