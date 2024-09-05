package com.example.eticaretsqflite.dao

import android.annotation.SuppressLint
import com.example.eticaretsqflite.model.Kategoriler
import com.example.eticaretsqlite.VertabaniYardimcisi

class KategorilerDao {

    @SuppressLint("Range")
    fun allCategories(vt:VertabaniYardimcisi):ArrayList<Kategoriler>{
        val db=vt.writableDatabase
        val kategoriListesi=ArrayList<Kategoriler>()

        val c=db.rawQuery("SELECT*FROM kategoriler",null)

        while (c.moveToNext()){
            val kategori= Kategoriler(c.getInt(c.getColumnIndex("kategori_id")),
                c.getString(c.getColumnIndex("kategori_adi")))

           kategoriListesi.add(kategori)

        }
        return kategoriListesi
    }
}