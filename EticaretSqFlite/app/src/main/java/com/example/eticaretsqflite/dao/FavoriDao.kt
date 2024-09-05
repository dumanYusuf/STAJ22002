package com.example.eticaretsqflite.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.eticaretsqflite.model.Kategoriler
import com.example.eticaretsqflite.model.Urunler
import com.example.eticaretsqlite.VertabaniYardimcisi

class FavoriDao {

    fun favoriEkle(vt: VertabaniYardimcisi, kullaniciId: Int, urunId: Int) {
        val db = vt.writableDatabase
        val values = ContentValues().apply {
            put("kullanici_id", kullaniciId)
            put("urun_id", urunId)
        }
        db.insert("favoriler", null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun favorileriGetir(vt: VertabaniYardimcisi, kullaniciId: Int): ArrayList<Urunler> {
        val db = vt.writableDatabase
        val favoriListesi = ArrayList<Urunler>()

        val query = """
        SELECT 
            f.favori_id, 
            f.kullanici_id, 
            u.urun_id, 
            u.urun_adi, 
            u.urun_fiyati, 
            u.urun_stok, 
            u.resim_adresi, 
            k.kategori_id, 
            k.kategori_adi
        FROM 
            favoriler f
        JOIN 
            urunler u ON f.urun_id = u.urun_id
        JOIN 
            kategoriler k ON u.kategori_id = k.kategori_id
        WHERE 
            f.kullanici_id = ?
    """

        val cursor = db.rawQuery(query, arrayOf(kullaniciId.toString()))

        while (cursor.moveToNext()) {
            val urun = Urunler(
                urun_id = cursor.getInt(cursor.getColumnIndex("urun_id")),
                urun_adi = cursor.getString(cursor.getColumnIndex("urun_adi")),
                urun_fiyati = cursor.getInt(cursor.getColumnIndex("urun_fiyati")),
                urun_stok = cursor.getInt(cursor.getColumnIndex("urun_stok")),
                kategoriler = Kategoriler(
                    kategori_id = cursor.getInt(cursor.getColumnIndex("kategori_id")),
                    kategori_adi = cursor.getString(cursor.getColumnIndex("kategori_adi"))
                ),
                resim_adresi = cursor.getString(cursor.getColumnIndex("resim_adresi"))
            )
            favoriListesi.add(urun)
        }

        cursor.close()
        return favoriListesi
    }

    fun favoriSil(vt: VertabaniYardimcisi, urunId: Int) {
        val db = vt.writableDatabase
        db.delete("favoriler","urun_id=?", arrayOf(urunId.toString()))
        db.close()
    }

}
