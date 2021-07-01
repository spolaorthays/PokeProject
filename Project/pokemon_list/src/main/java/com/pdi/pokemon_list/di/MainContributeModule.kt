package com.pdi.pokemon_list.di

import androidx.lifecycle.ViewModel
import com.pdi.pokemon_list.data.local.PokemonRepository
import com.pdi.pokemon_list.data.service.PokemonService
import com.pdi.pokemon_list.domain.PokemonContract
import com.pdi.pokemon_list.domain.PokemonInteractor
import com.pdi.pokemon_list.view.MainActivity
import com.pdi.pokemon_list.view.MainViewModel
import com.pdi.share.di.ManageThreadModule
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module(includes = [(ManageThreadModule::class)])
abstract class MainContributeModule {
    @ContributesAndroidInjector(modules = [(MainModule::class), (MainContributesModule::class)])
    abstract fun provideView(): MainActivity
}

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindLoginViewModel(viewModel: MainViewModel): ViewModel
}

@Module
class MainContributesModule {

    @Provides
    fun providePokemonInteractor(impl: PokemonInteractor): PokemonContract.Interactor = impl

    @Provides
    fun providePokemonRepository(impl: PokemonRepository): PokemonContract.Repository = impl

    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService = retrofit.create(PokemonService::class.java)
}