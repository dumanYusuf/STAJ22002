package com.example.eticaretsqflite.adepter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.eticaretsqflite.R
import com.example.eticaretsqflite.model.Musteri
import com.example.eticaretsqlite.VertabaniYardimcisi

class AdepterPerson(private val mcontext: Context?,
                    private var musteriListesi:List<Musteri>,
                    private val vt: VertabaniYardimcisi
):RecyclerView.Adapter<AdepterPerson.CardTasarimNesneleriTututucu>(){

    companion object {
        private const val PREFS_NAME = "AppPrefs"
        private const val DARK_MODE_KEY = "dark_mode"
    }

    inner  class CardTasarimNesneleriTututucu(view: View): RecyclerView.ViewHolder(view){
        var textViewKullanicName: TextView
        var textViewUsername: TextView
        var imageViewSepetGit:ImageView
        var imageViewFavoriGit:ImageView
        var imageViewKategorilerGitt:ImageView
        var switchDark:Switch


        init {
            textViewKullanicName=view.findViewById(R.id.textViewKullanicName)
            textViewUsername=view.findViewById(R.id.textViewUsername)
            imageViewSepetGit=view.findViewById(R.id.imageViewSepetGit)
            imageViewFavoriGit=view.findViewById(R.id.imageViewFavoriGit)
            imageViewKategorilerGitt=view.findViewById(R.id.imageViewKategorilerGitt)
            switchDark=view.findViewById(R.id.switchDark)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimNesneleriTututucu {
        val tasarim=
            LayoutInflater.from(mcontext).inflate(R.layout.person_card_tasarim,parent,false)
        return  CardTasarimNesneleriTututucu(tasarim)
    }

    override fun getItemCount(): Int {
       return musteriListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimNesneleriTututucu, position: Int) {
        val musteri=musteriListesi.get(position)

        holder.textViewUsername.text=musteri.musteri_email
        holder.textViewKullanicName.text=musteri.musteri_adi

        holder.imageViewSepetGit.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_personFragment_to_basketFragment)
        }

        holder.imageViewFavoriGit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_personFragment_to_favoritesFragment)
        }
        holder.imageViewKategorilerGitt.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_personFragment_to_homeFragment)
        }

        val prefs = holder.itemView.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkMode = prefs.getBoolean(DARK_MODE_KEY, false)
        holder.switchDark.isChecked = isDarkMode

        holder.switchDark.setOnCheckedChangeListener { _, isChecked ->
            val mode = if (isChecked) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }

            AppCompatDelegate.setDefaultNightMode(mode)

            val editor = holder.itemView.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
            editor.putBoolean(DARK_MODE_KEY, isChecked)
            editor.apply()
        }



    }

}
