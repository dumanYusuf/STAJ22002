package com.example.eticaretsqflite.dao

import android.annotation.SuppressLint
import com.example.eticaretsqflite.model.Musteri
import com.example.eticaretsqlite.VertabaniYardimcisi

class KullaniciDao {


    @SuppressLint("Range")
    fun kullaniciGetir(vt:VertabaniYardimcisi):ArrayList<Musteri>{
        val db=vt.writableDatabase
        val musteriListesi=ArrayList<Musteri>()

        val c=db.rawQuery("SELECT*FROM musteriler",null)

        while (c.moveToNext()){
            val musteri= Musteri(c.getInt(c.getColumnIndex("musteri_id")),
                c.getString(c.getColumnIndex("musteri_adi")),
                        c.getString(c.getColumnIndex("musteri_email")),
                        c.getString(c.getColumnIndex("musteri_password")))

            musteriListesi.add(musteri)
        }
        return musteriListesi
    }
}