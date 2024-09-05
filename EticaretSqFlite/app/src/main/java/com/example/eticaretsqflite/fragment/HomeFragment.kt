package com.example.eticaretsqflite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretsqflite.adepter.AdepterKategori
import com.example.eticaretsqflite.dao.KategorilerDao
import com.example.eticaretsqflite.databinding.FragmentHomeBinding
import com.example.eticaretsqflite.model.Kategoriler
import com.example.eticaretsqlite.VertabaniYardimcisi
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper


class HomeFragment : Fragment(){

       private lateinit var kategoriListesi:ArrayList<Kategoriler>
       private lateinit var adepterKategori: AdepterKategori
       private lateinit var vt:VertabaniYardimcisi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val binding=FragmentHomeBinding.inflate(inflater,container,false)

        veriTabaniKopyala()

    //  binding.toolbarAnaSayfa.title="E-COMMERCE"

      binding.kategoriRv.setHasFixedSize(true)
      binding.kategoriRv.layoutManager=LinearLayoutManager(context)


          vt=VertabaniYardimcisi(context)
          kategoriListesi= KategorilerDao().allCategories(vt)
          adepterKategori= AdepterKategori(context,kategoriListesi)
          binding.kategoriRv.adapter=adepterKategori

        return binding.root
    }


   /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_ara, menu)
        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("aranan harf",query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("aranan jelime",newText)
        return true
    }
*/

    fun veriTabaniKopyala(){
        val copyHelper=DatabaseCopyHelper(context)
        try {
            copyHelper.createDataBase()
           copyHelper.openDataBase()
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

}

