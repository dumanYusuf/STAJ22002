package com.example.eticaretsqflite.adepter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eticaretsqflite.R
import com.example.eticaretsqflite.dao.SapetDao
import com.example.eticaretsqflite.model.Urunler
import com.example.eticaretsqlite.VertabaniYardimcisi
import com.google.android.material.snackbar.Snackbar

class AdepterBasket(private val mcontext: Context?,
                    private var sepetListesi:List<Urunler>,
                    private val vt: VertabaniYardimcisi)
    :RecyclerView.Adapter<AdepterBasket.CardTasarimNesneleriTututucu>(){

    inner  class CardTasarimNesneleriTututucu(view: View): RecyclerView.ViewHolder(view){
        var imageViewSepet: ImageView
        var textViewSepetUrunAd: TextView
        var textViewSepetUrunFiyat: TextView
        var imageViewSepetDelete: ImageView

        init {
            imageViewSepet=view.findViewById(R.id.imageViewSepet)
            textViewSepetUrunAd=view.findViewById(R.id.textViewSepetUrunAd)
            textViewSepetUrunFiyat=view.findViewById(R.id.textViewSepetUrunFiyat)
            imageViewSepetDelete=view.findViewById(R.id.imageViewSepetDelete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimNesneleriTututucu {
        val tasarim=
            LayoutInflater.from(mcontext).inflate(R.layout.sepet_card_tasarim,parent,false)
        return  CardTasarimNesneleriTututucu(tasarim)
    }

    override fun getItemCount(): Int {
      return sepetListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimNesneleriTututucu, position: Int) {
        val favori=sepetListesi.get(position)

        holder.textViewSepetUrunAd.text=favori.urun_adi
        holder.textViewSepetUrunFiyat.text="${favori.urun_fiyati} Tl"

        holder.imageViewSepetDelete.setOnClickListener {z->

            val sb= Snackbar.make(z,"${favori.urun_adi} Sepetten Çıkarılsın mı?", Snackbar.LENGTH_LONG)
            sb.setAction("Evet"){y->
                SapetDao().sepetSil(vt,favori.urun_id)
                sepetListesi= SapetDao().sepetiGetir(vt,1)
                notifyDataSetChanged()
            }
            sb.setActionTextColor(Color.RED)
            sb.setTextColor(Color.BLUE)
            sb.setBackgroundTint(Color.WHITE)

            sb.show()

        }

        Glide.with(holder.itemView.context)
            .load(favori.resim_adresi)
            .into(holder.imageViewSepet)
    }

}