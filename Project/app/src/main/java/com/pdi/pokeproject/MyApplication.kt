package com.pdi.pokeproject

import android.app.Application
import com.pdi.network.di.NetworkModule
import com.pdi.pokemon_list.MainContributeModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (MainContributeModule::class), NetworkModule::class])
interface ApplicationComponent: AndroidInjector<DaggerApplication> {
    fun inject(application: MyApplication)
    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}

class MyApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
        return appComponent
    }
}