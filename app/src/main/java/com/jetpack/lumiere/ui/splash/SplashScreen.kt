package com.jetpack.lumiere.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.jetpack.lumiere.R
import com.jetpack.lumiere.ui.home.MainActivity

class SplashScreen : AppCompatActivity() {
    private val delay: Int = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(mainLooper).postDelayed({
            val intentToMain = Intent(this, MainActivity::class.java)
            startActivity(intentToMain)
            finish()
        }, delay.toLong())
    }
}