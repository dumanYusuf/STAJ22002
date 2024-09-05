package com.example.eticaretsqflite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretsqflite.adepter.AdepterFavori
import com.example.eticaretsqflite.dao.FavoriDao
import com.example.eticaretsqflite.databinding.FragmentFavoritesBinding
import com.example.eticaretsqlite.VertabaniYardimcisi

class FavoritesFragment : Fragment() {

    private lateinit var vt: VertabaniYardimcisi
    private lateinit var adepterFavori: AdepterFavori

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding=FragmentFavoritesBinding.inflate(inflater,container,false)

        binding.toolbarFavorites.title="E-COMMERCE"

        binding.favoriRv.setHasFixedSize(true)
        binding.favoriRv.layoutManager=LinearLayoutManager(context)


        vt= VertabaniYardimcisi(context)
        val favoriListesi= FavoriDao().favorileriGetir(vt,1)

        adepterFavori= AdepterFavori(context,favoriListesi,vt)
        binding.favoriRv.adapter=adepterFavori

        return binding.root
    }


}