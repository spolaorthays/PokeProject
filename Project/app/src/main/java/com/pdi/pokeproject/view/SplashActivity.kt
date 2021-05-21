package com.pdi.pokeproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pdi.pokeproject.R

class SplashActivity : AppCompatActivity() {

    companion object {
        const val TIMER = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, com.pdi.pokemon_list.MainActivity::class.java))
            finish()
        }, TIMER)
    }
}