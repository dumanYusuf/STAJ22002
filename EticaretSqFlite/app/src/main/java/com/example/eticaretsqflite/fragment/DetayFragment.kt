package com.example.eticaretsqflite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.eticaretsqflite.databinding.FragmentDetayBinding
import com.example.eticaretsqflite.model.Urunler

class DetayFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding=FragmentDetayBinding.inflate(inflater,container,false)


        val gelenUrun=arguments?.getSerializable("nesne")as Urunler
        binding.toolbarDetay.title="${gelenUrun.urun_adi}"

        binding.textViewDetaySSAd.text=gelenUrun.urun_adi
        binding.textViewDetayFiyat.text="${gelenUrun.urun_fiyati} Tl"

        context?.let {
            Glide.with(it)
                .load(gelenUrun.resim_adresi)
                .into(binding.imageViewDetay)
        }

        return binding.root
    }


}