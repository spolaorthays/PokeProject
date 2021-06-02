package com.pdi.pokeproject

import android.app.Application
import com.pdi.network.di.NetworkModule
import com.pdi.pokemon_list.di.MainContributeModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (MainContributeModule::class), (NetworkModule::class), (AndroidSupportInjectionModule::class)])
interface AppComponent: AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}

class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}