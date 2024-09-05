package com.example.eticaretsqflite.adepter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eticaretsqflite.dao.FavoriDao
import com.example.eticaretsqflite.R
import com.example.eticaretsqflite.model.Urunler
import com.example.eticaretsqlite.VertabaniYardimcisi
import com.google.android.material.snackbar.Snackbar

class AdepterFavori(
    private val mcontext: Context?,
    private var sepetListesi:List<Urunler>,
    private val vt:VertabaniYardimcisi)
    :RecyclerView.Adapter<AdepterFavori.CardTasarimNesneleriTututucu>(){



    inner  class CardTasarimNesneleriTututucu(view: View): RecyclerView.ViewHolder(view){
        var imageViewFavori: ImageView
        var textViewFavoriUrunAd: TextView
        var textViewFavoriUrunFiyat: TextView
        var imageViewdelete:ImageView

        init {
            imageViewFavori=view.findViewById(R.id.imageViewFavori)
            textViewFavoriUrunAd=view.findViewById(R.id.textViewFavoriUrunAd)
            textViewFavoriUrunFiyat=view.findViewById(R.id.textViewFavoriUrunFiyat)
            imageViewdelete=view.findViewById(R.id.imageViewdelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimNesneleriTututucu {
        val tasarim=
            LayoutInflater.from(mcontext).inflate(R.layout.favori_card_tasarim,parent,false)
        return  CardTasarimNesneleriTututucu(tasarim)
    }

    override fun getItemCount(): Int {
        return  sepetListesi.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: CardTasarimNesneleriTututucu, position: Int) {
        val favori=sepetListesi.get(position)

        holder.textViewFavoriUrunAd.text=favori.urun_adi
        holder.textViewFavoriUrunFiyat.text="${favori.urun_fiyati} TL"

        holder.imageViewdelete.setOnClickListener {z->
            val sb=Snackbar.make(z,"${favori.urun_adi} Favorilerden Çıkarılsın mı?",Snackbar.LENGTH_LONG)
            sb.setAction("Evet"){y->
                FavoriDao().favoriSil(vt,favori.urun_id)
                sepetListesi= FavoriDao().favorileriGetir(vt,1)
                notifyDataSetChanged()
            }
            sb.setActionTextColor(Color.RED)
            sb.setTextColor(Color.BLUE)
            sb.setBackgroundTint(Color.WHITE)

            sb.show()
        }

        mcontext.let {
            Glide.with(holder.itemView.context)
                .load(favori.resim_adresi)
                .into(holder.imageViewFavori)
        }


    }

}