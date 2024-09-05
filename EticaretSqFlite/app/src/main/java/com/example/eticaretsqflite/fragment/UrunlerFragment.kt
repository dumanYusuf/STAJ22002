package com.example.eticaretsqflite.fragment

import com.example.eticaretsqflite.dao.UrunlerDao
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.eticaretsqflite.adepter.AdepterUrunler
import com.example.eticaretsqflite.databinding.FragmentUrunlerBinding
import com.example.eticaretsqflite.model.Kategoriler
import com.example.eticaretsqflite.model.Urunler
import com.example.eticaretsqlite.VertabaniYardimcisi

class UrunlerFragment : Fragment() {

        private lateinit var urunListesi:ArrayList<Urunler>
        private lateinit var adepterUrunler: AdepterUrunler
        private lateinit var vt:VertabaniYardimcisi

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val binding=FragmentUrunlerBinding.inflate(inflater,container,false)

        val gelenKategori=arguments?.getSerializable("nesne")as Kategoriler

        binding.toolbarUrunler.title="${gelenKategori.kategori_adi}"

           binding.urunlerRv.setHasFixedSize(true)
           binding.urunlerRv.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

           vt= VertabaniYardimcisi(context)
          urunListesi= UrunlerDao().allCategoriesFilterProducts(vt, gelenKategori.kategori_id)

          adepterUrunler= AdepterUrunler(context,urunListesi)
           binding.urunlerRv.adapter=adepterUrunler


        return binding.root
    }


}