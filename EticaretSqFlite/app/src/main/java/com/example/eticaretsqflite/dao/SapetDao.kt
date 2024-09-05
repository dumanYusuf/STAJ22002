package com.example.eticaretsqflite.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.eticaretsqflite.model.Kategoriler
import com.example.eticaretsqflite.model.Urunler
import com.example.eticaretsqlite.VertabaniYardimcisi

class SapetDao {

    fun sepetEkle(vt: VertabaniYardimcisi, musteriId: Int, urunId: Int, siparisAdedi: Int) {
        val db = vt.writableDatabase
        val values = ContentValues().apply {
            put("urun_id", urunId)
            put("musteri_id", musteriId)
            put("siparis_adeti", siparisAdedi)
            put("siparis_tarihi", System.currentTimeMillis())
        }
        db.insert("siparisler", null, values)
        db.close()
    }


    @SuppressLint("Range")
    fun sepetiGetir(vt: VertabaniYardimcisi, urunId: Int): ArrayList<Urunler> {
        val db = vt.writableDatabase
        val sepetListesi = ArrayList<Urunler>()

        val query = """
    SELECT 
        s.siparis_id,
        s.urun_id,
        s.musteri_id,
        s.siparis_adeti,
        s.siparis_tarihi,
        u.urun_id, 
        u.urun_adi, 
        u.urun_fiyati, 
        u.urun_stok, 
        u.resim_adresi, 
        k.kategori_id, 
        k.kategori_adi
    FROM 
        siparisler s
    JOIN 
        urunler u ON s.urun_id = u.urun_id
    JOIN 
        kategoriler k ON u.kategori_id = k.kategori_id
    WHERE 
        s.urun_id = ?
    """

        val cursor = db.rawQuery(query, arrayOf(urunId.toString()))

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
                resim_adresi = cursor.getString(cursor.getColumnIndex("resim_adresi")),
            )
            sepetListesi.add(urun)
        }

        cursor.close()
        db.close()

        return sepetListesi
    }


    fun sepetSil(vt: VertabaniYardimcisi, urunId: Int) {
        val db = vt.writableDatabase
        db.delete("siparisler","urun_id=?", arrayOf(urunId.toString()))
        db.close()
    }

}