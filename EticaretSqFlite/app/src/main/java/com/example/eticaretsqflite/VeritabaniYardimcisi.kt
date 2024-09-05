package com.example.eticaretsqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VertabaniYardimcisi(context: Context?):SQLiteOpenHelper(context,"ticaret.sqlite",null,1) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"kategoriler\" (\n" +
                "\t\"kategori_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"kategori_adi\"\tTEXT NOT NULL\n" +
                ");")


        db?.execSQL("" +
                "CREATE TABLE IF NOT EXISTS \"musteriler\" (\n" +
                "\t\"musteri_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"musteri_adi\"\tTEXT NOT NULL,\n" +
                "\t\"musteri_email\"\tTEXT NOT NULL,\n" +
                "\t\"musteri_password\"\tTEXT NOT NULL\n" +
                ")")

        db?.execSQL("CREATE TABLE IF NOT EXISTS \"favoriler\" (\n" +
                "\t\"favori_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"kullanici_id\"\tINTEGER NOT NULL,\n" +
                "\t\"urun_id\"\tINTEGER NOT NULL,\n" +
                "\tFOREIGN KEY(\"urun_id\") REFERENCES \"urunler\"(\"urun_id\"),\n" +
                "\tFOREIGN KEY(\"kullanici_id\") REFERENCES \"musteriler\"(\"musteri_id\")\n" +
                ")")

        db?.execSQL("" +
                "CREATE TABLE IF NOT EXISTS \"siparisler\" (\n" +
                "\t\"siparis_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"urun_id\"\tINTEGER NOT NULL,\n" +
                "\t\"musteri_id\"\tINTEGER NOT NULL,\n" +
                "\t\"siparis_adeti\"\tINTEGER NOT NULL,\n" +
                "\t\"siparis_tarihi\"\tTEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                "\tFOREIGN KEY(\"urun_id\") REFERENCES \"urunler\"(\"urun_id\"),\n" +
                "\tFOREIGN KEY(\"musteri_id\") REFERENCES \"musteriler\"(\"musteri_id\")\n" +
                ")")

        db?.execSQL("" +
                "CREATE TABLE IF NOT EXISTS \"urunler\" (\n" +
                "\t\"urun_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"urun_adi\"\tTEXT NOT NULL,\n" +
                "\t\"urun_fiyati\"\tINTEGER NOT NULL,\n" +
                "\t\"urun_stok\"\tINTEGER NOT NULL,\n" +
                "\t\"kategori_id\"\tINTEGER NOT NULL DEFAULT 1,\n" +
                "\t\"resim_adresi\"\tTEXT NOT NULL DEFAULT 'https://www.casper.com.tr/uploads/2023/05/g770-tablet_op.webp'\n" +
                ")")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF  EXISTS kategoriler")
        db?.execSQL("DROP TABLE IF  EXISTS musteriler")
        db?.execSQL("DROP TABLE IF  EXISTS favoriler")
        db?.execSQL("DROP TABLE IF  EXISTS siparisler")
        db?.execSQL("DROP TABLE IF  EXISTS urunler")

        onCreate(db)


    }


}