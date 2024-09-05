package com.example.eticaretsqflite.dao

import android.annotation.SuppressLint
import com.example.eticaretsqflite.model.Kategoriler
import com.example.eticaretsqflite.model.Urunler
import com.example.eticaretsqlite.VertabaniYardimcisi

class UrunlerDao {


    @SuppressLint("Range")
    fun allCategoriesFilterProducts(vt: VertabaniYardimcisi, kategoriId: Int): ArrayList<Urunler> {
        val db = vt.writableDatabase
        val urunListesi = ArrayList<Urunler>()

        val c = db.rawQuery("SELECT * FROM kategoriler, urunler WHERE kategoriler.kategori_id = urunler.kategori_id AND urunler.kategori_id = $kategoriId", null)

        while (c.moveToNext()) {
            val kategori = Kategoriler(
                c.getInt(c.getColumnIndex("kategori_id")),
                c.getString(c.getColumnIndex("kategori_adi"))
            )

            val urun = Urunler(
                c.getInt(c.getColumnIndex("urun_id")),
                c.getString(c.getColumnIndex("urun_adi")),
                c.getInt(c.getColumnIndex("urun_fiyati")),
                c.getInt(c.getColumnIndex("urun_stok")),
                kategori,
                c.getString(c.getColumnIndex("resim_adresi")),
            )

            urunListesi.add(urun)
        }
        return urunListesi
    }
}
