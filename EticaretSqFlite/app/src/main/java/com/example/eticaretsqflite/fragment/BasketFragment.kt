package com.example.eticaretsqflite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretsqflite.adepter.AdepterBasket
import com.example.eticaretsqflite.dao.SapetDao
import com.example.eticaretsqflite.databinding.FragmentBasketBinding
import com.example.eticaretsqlite.VertabaniYardimcisi

class BasketFragment : Fragment() {

    private lateinit var vt: VertabaniYardimcisi
    private lateinit var adepterBasket: AdepterBasket

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding=FragmentBasketBinding.inflate(inflater,container,false)



        binding.toolbarSepet.title="E-COMMERCE"
        binding.sepetRv.setHasFixedSize(true)
        binding.sepetRv.layoutManager=LinearLayoutManager(context)

         vt=VertabaniYardimcisi(context)
        val basketListesi= SapetDao().sepetiGetir(vt,1)

        adepterBasket= AdepterBasket(context,basketListesi,vt)
        binding.sepetRv.adapter=adepterBasket

        return binding.root
    }


}