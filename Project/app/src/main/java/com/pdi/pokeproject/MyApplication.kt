package com.pdi.pokeproject

import android.app.Application
import com.pdi.network.Retrofit
import com.pdi.pokeproject.view.SplashActivity

interface ApplicationComponent {
    fun inject(view: SplashActivity) //Exemplo
}

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //val appComponent = DaggerApplicationComponent.create()
        Retrofit.initialize(this)
    }
}