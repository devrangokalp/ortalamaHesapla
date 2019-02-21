package com.devrangokalp.ortalamahesapla

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsSpinner
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.yeni_ders_layout.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class MainActivity : AppCompatActivity() {

    private val DERSLER= arrayOf("Matematik","Türkçe","Fizik","Kimya","Biyoloji","Edebiyat","Tarih","Algoritma")
    private  var tumNotlar:ArrayList<Dersler> =ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            var adapter=ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,DERSLER)
            etDersAdi.setAdapter(adapter)
          //  spnDersNotu.setAdapter(adapter)


        if (rootLayout.childCount == 0) {
            btnHesapla.visibility = View.INVISIBLE
        } else
            btnHesapla.visibility = View.VISIBLE



        btnDersEkle.setOnClickListener {

            if (!etDersAdi.text.isNullOrEmpty()) {
                var inflater = LayoutInflater.from(this)  //xml i inflater a dönüştürme
                //var inflater2=layoutInflater
                /*  var inflater3=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
              inflater3.inflate()
              */

                var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout, null)
                yeniDersView.etYeniDersAdi.setAdapter(adapter)

                var dersAdi = etDersAdi.text.toString()
                var dersKredisi = spnDersKredisi.selectedItem.toString()
                var dersHarfi = spnDersNotu.selectedItem.toString()

                yeniDersView.etYeniDersAdi.setText(dersAdi)
                yeniDersView.spnYeniDersKredisi.setSelection(spinnerIndexBul(spnDersKredisi, dersKredisi))
                yeniDersView.spnYeniDersNotu.setSelection(spinnerIndexBul(spnDersNotu, dersHarfi))

                yeniDersView.btnDersSil.setOnClickListener {
                    rootLayout.removeView(yeniDersView)
                    if (rootLayout.childCount == 0) {
                        btnHesapla.visibility = View.INVISIBLE
                    } else
                        btnHesapla.visibility = View.VISIBLE
                }

                rootLayout.addView(yeniDersView)
                btnHesapla.visibility = View.VISIBLE
                sifirla()


            }else
            {FancyToast.makeText(baseContext,"Ders adını giriniz",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()}
        }


    }

    fun ortalamaHesapla(view: View) {
        var toplamNot:Double=0.0
        var toplamKredi:Double=0.0
        //var ort:Double=(toplamNot/toplamKredi).toDouble()

        for (i in 0..rootLayout.childCount -1){
            var teksatir=rootLayout.getChildAt(i)
            var geciciDers=Dersler(teksatir.etYeniDersAdi.text.toString(),teksatir.spnYeniDersKredisi.selectedItem.toString(),teksatir.spnYeniDersNotu.selectedItem.toString())
            tumNotlar.add(geciciDers)
        }

        for (oAnkiDers in tumNotlar){
            toplamNot+=harfiNotaCevir(oAnkiDers.dersNou)*(oAnkiDers.dersKredi.toDouble())
            toplamKredi+=oAnkiDers.dersKredi.toDouble()

        }
        FancyToast.makeText(this,"Ortalamanız:"+(toplamNot/toplamKredi),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
        tumNotlar.clear()
    }


    fun harfiNotaCevir(gelenNotHarfi:String):Double
    {
        var deger=0.0
        when(gelenNotHarfi)
        {
            "AA" ->deger=4.0
            "BA" ->deger=3.5
            "BB" ->deger=3.0
            "CB" ->deger=2.5
            "CC" ->deger=2.0
            "DC" ->deger=1.5
            "DD" ->deger=1.0
            "FF"  ->deger=0.0
        }
        return deger
    }

    fun spinnerIndexBul(spinner: Spinner, aranacakDeger: String): Int {
        var index = 0
        for (i in 0..spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(aranacakDeger)) {
                index = i
                break
            }
        }
        return index
    }
    fun sifirla()
    {
        etDersAdi.setText("")
        spnDersKredisi.setSelection(0)
        spnDersNotu.setSelection(0)

    }


}
