package com.pdi.pokeproject.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pdi.pokemon_list.view.MainActivity
import com.pdi.pokeproject.R


class SplashActivity : AppCompatActivity() {

    companion object {
        const val TIMER = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        configSplash()
    }

    private fun configSplash() {
        val imageView = findViewById<View>(R.id.gif_splash) as ImageView
        Glide
            .with(this)
            .asGif()
            .load(R.raw.pikachu_splash)
            .circleCrop()
            .into(imageView)

        configTimeSplash()
    }

    private fun configTimeSplash() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIMER)
    }
}