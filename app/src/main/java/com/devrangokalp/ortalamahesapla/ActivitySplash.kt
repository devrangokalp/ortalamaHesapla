package com.devrangokalp.ortalamahesapla

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var asagidanGelenButton = AnimationUtils.loadAnimation(this, R.anim.asagidan_gelen_button)
        btnOrtalamaHesapla.animation = asagidanGelenButton
        var yukaridanGelenBalon = AnimationUtils.loadAnimation(this, R.anim.yukaridan_gelen_balon)
        imgBalon.animation = yukaridanGelenBalon
        var asagiGeriGidenButton=AnimationUtils.loadAnimation(this,R.anim.asagi_giden_button)
        var yukariGeriGidenBalon=AnimationUtils.loadAnimation(this,R.anim.yukari_giden_balon)

        btnOrtalamaHesapla.setOnClickListener {

            btnOrtalamaHesapla.startAnimation(asagiGeriGidenButton)
            imgBalon.startAnimation(yukariGeriGidenBalon)

            object :CountDownTimer(1000,1000){
                override fun onFinish() {
                    var intent=Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)

                }

                override fun onTick(millisUntilFinished: Long) {


                }
            }.start()



        }


    }
}
