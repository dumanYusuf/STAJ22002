package com.example.eticaretsqflite.model

import java.io.Serializable

data class Favoriler(
    var favori_id:Int,
    var kullanici_id: Musteri,
    var urun_id: Urunler,
):Serializable {
}