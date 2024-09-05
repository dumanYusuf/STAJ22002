package com.example.eticaretsqflite.model

import java.io.Serializable

data class Sepet(
    var siparis_id:Int,
    var urun_id: Urunler,
    var musteri_id: Musteri,
    var siparis_adeti: Int,
    var siparis_tarihi:String): Serializable {
}