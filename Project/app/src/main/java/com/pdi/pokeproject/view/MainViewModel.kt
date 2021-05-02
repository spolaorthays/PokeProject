package com.pdi.pokeproject.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pdi.pokeproject.ManageThreads
import com.pdi.network.data.Pokemon
import com.pdi.network.data.PokemonDetails
import com.pdi.pokeproject.domain.PokemonContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel(
        private val interactor: PokemonContract.Interactor,
        private val scheduler: ManageThreads
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonListFormat = MutableLiveData<PokemonDetails>()

    fun getPokemonsFromInteractor(context: Context) {
        compositeDisposable.add(
                interactor.getPokemonListFromRepository()
                        .subscribeOn(scheduler.io)
                        .observeOn(scheduler.main)
                        .subscribeBy(
                                onSuccess = {
                                    pokemonList.value = it
                                    it.forEach { pokemon ->
                                        getPokemonDetailFromInteractor(pokemon.url)
                                    }
                                },
                                onError = {
                                    Log.e("Errouuuuu", "Erro ao consultar a api: ${it.message}")
                                }
                        )
        )
    }

    fun getPokemonDetailFromInteractor(url: String) {
        compositeDisposable.add(
                interactor.getPokemonDetailsFromRepository(url)
                        .subscribeOn(scheduler.io)
                        .observeOn(scheduler.main)
                        .subscribeBy(
                                onSuccess = {
                                    pokemonListFormat.value = it
                                }, onError = {
                                    Log.e("Errouuuuu", "Erro ao consultar a api: ${it.message}")
                                }
                        )
        )
    }
}