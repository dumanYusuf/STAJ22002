package com.example.eticaretsqflite.model

import java.io.Serializable

data class Musteri(
    var musteri_id:Int,
    var musteri_adi:String,
    var musteri_email:String,
    var musteri_password: String
):Serializable {
}