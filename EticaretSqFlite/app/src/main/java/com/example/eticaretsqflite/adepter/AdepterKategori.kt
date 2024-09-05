package com.example.eticaretsqflite.adepter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.eticaretsqflite.model.Kategoriler
import com.example.eticaretsqflite.R

class AdepterKategori(private val mcontext: Context?, private val kategoriListesi:List<Kategoriler>)
    :RecyclerView.Adapter<AdepterKategori.CardTasarimNesneleriTututucu>()
{

    inner  class CardTasarimNesneleriTututucu(view: View):RecyclerView.ViewHolder(view){

       var kategori_card:CardView
       var textViewKategoriName:TextView

       init {
           kategori_card=view.findViewById(R.id.kategori_card)
           textViewKategoriName=view.findViewById(R.id.textViewKategoriName)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimNesneleriTututucu {
        val tasarimYol=LayoutInflater.from(mcontext).inflate(R.layout.kategori_card_tasarim,parent,false)
        return CardTasarimNesneleriTututucu(tasarimYol)
    }

    override fun getItemCount(): Int {
       return kategoriListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimNesneleriTututucu, position: Int) {
       val kategori=kategoriListesi.get(position)

        holder.textViewKategoriName.text=kategori.kategori_adi

        holder.kategori_card.setOnClickListener {

            val bundle=Bundle()
            bundle.putSerializable("nesne",kategori)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_urunlerFragment,bundle)
        }
    }

}