package com.example.eticaretsqflite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretsqflite.adepter.AdepterPerson
import com.example.eticaretsqflite.dao.KullaniciDao
import com.example.eticaretsqflite.databinding.FragmentPersonBinding
import com.example.eticaretsqlite.VertabaniYardimcisi

class PersonFragment : Fragment() {
    private lateinit var adepter: AdepterPerson
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val binding=FragmentPersonBinding.inflate(inflater,container,false)


        binding.toolbarPerson.title="E-COMMERCE"
        binding.personRv.setHasFixedSize(true)
        binding.personRv.layoutManager=LinearLayoutManager(context)

        val vt=VertabaniYardimcisi(context)

        val musteriListem= KullaniciDao().kullaniciGetir(vt)
        adepter= AdepterPerson(context,musteriListem,vt)
        binding.personRv.adapter=adepter


        return binding.root
    }


}