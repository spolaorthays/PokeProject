package com.pdi.pokeproject.view

import androidx.lifecycle.ViewModel
import com.pdi.pokeproject.data.PokemonRepository
import com.pdi.pokeproject.domain.PokemonInteractor
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
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
    fun providePokemonInteractor(impl: PokemonInteractor) = impl

    @Provides
    fun providePokemonRepository(impl: PokemonRepository) = impl
}