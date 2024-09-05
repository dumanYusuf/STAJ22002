package com.example.eticaretsqflite.adepter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eticaretsqflite.dao.FavoriDao
import com.example.eticaretsqflite.R
import com.example.eticaretsqflite.dao.SapetDao
import com.example.eticaretsqflite.model.Urunler
import com.example.eticaretsqlite.VertabaniYardimcisi
import com.google.android.material.snackbar.Snackbar

class AdepterUrunler (private val mcontext: Context?, private val urunListesi:List<Urunler>)
    :RecyclerView.Adapter<AdepterUrunler.CardTasarimNesneleriTututucu>()

{

    inner  class CardTasarimNesneleriTututucu(view: View): RecyclerView.ViewHolder(view){

        var cardFilimler:CardView
        var imageViewFavoriSepet: ImageView
        var imageViewUrun: ImageView
        var textViewUrunFiyat:TextView
        var textViewUrunAd:TextView
        var imageViewSepet:ImageView

        init {
            cardFilimler=view.findViewById(R.id.cardFilimler)
            imageViewFavoriSepet=view.findViewById(R.id.imageViewFavoriSepet)
            imageViewUrun=view.findViewById(R.id.imageViewUrun)
            textViewUrunFiyat=view.findViewById(R.id.textViewUrunFiyat)
            textViewUrunAd=view.findViewById(R.id.textViewUrunAd)
            imageViewSepet=view.findViewById(R.id.imageViewSepet)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): CardTasarimNesneleriTututucu {
       val tasarim=LayoutInflater.from(mcontext).inflate(R.layout.urunler_card_tasarim,parent,false)
        return  CardTasarimNesneleriTututucu(tasarim)
    }

    override fun getItemCount(): Int {
    return  urunListesi.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: CardTasarimNesneleriTututucu, position: Int) {
       val urun=urunListesi.get(position)

        holder.textViewUrunAd.text=urun.urun_adi
        holder.textViewUrunFiyat.text="${urun.urun_fiyati} TL"

        holder.imageViewSepet.setOnClickListener {x->

            val urun_id=urun.urun_id
            val kullanici=1
            val sepetDao= SapetDao()
            sepetDao.sepetEkle(VertabaniYardimcisi(mcontext),1,kullanici,urun_id)
            val sb= Snackbar.make(x,"${urun.urun_adi} Sepete eklendi", Snackbar.LENGTH_LONG)
            sb.setActionTextColor(Color.RED)
            sb.setTextColor(Color.BLUE)
            sb.setBackgroundTint(Color.WHITE)

            sb.show()
            println("Ürün ID: $urun_id sepete eklendi.")

        }

        holder.imageViewFavoriSepet.setOnClickListener {nesne->

            val urun_id=urun.urun_id
            val kullanici=1
            val favoriDao= FavoriDao()
            favoriDao.favoriEkle(VertabaniYardimcisi(mcontext),kullanici,urun_id)
            val sb= Snackbar.make(nesne,"${urun.urun_adi} Favorilere eklendi", Snackbar.LENGTH_LONG)
            sb.setActionTextColor(Color.RED)
            sb.setTextColor(Color.BLUE)
            sb.setBackgroundTint(Color.WHITE)

            sb.show()
            println("Ürün ID: $urun_id favorilere eklendi.")
        }

        holder.cardFilimler.setOnClickListener {

            val bundle= Bundle()
            bundle.putSerializable("nesne",urun)
            Navigation.findNavController(it).navigate(R.id.action_urunlerFragment_to_detayFragment,bundle)
        }

        mcontext?.let {
            Glide.with(holder.itemView.context)
                .load(urun.resim_adresi)
                .into(holder.imageViewUrun)
        }


    }


}