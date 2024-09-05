package com.example.eticaretsqflite.model

import java.io.Serializable

data class Urunler(
    var urun_id:Int,
    var urun_adi:String,
    var urun_fiyati:Int,
    var urun_stok:Int,
    var kategoriler: Kategoriler,
    var resim_adresi: String
):Serializable {
}