package com.example.tic_tac_toe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen2.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen2)

        val intent = Intent(this,MainActivity::class.java)
        val scale = AnimationUtils.loadAnimation(this,R.anim.scale2)
        imgtemplate.startAnimation(scale)
        val alpha = AnimationUtils.loadAnimation(this,R.anim.alpha)
        imgheadersplachscreen.startAnimation(alpha)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        },2000)
    }
}