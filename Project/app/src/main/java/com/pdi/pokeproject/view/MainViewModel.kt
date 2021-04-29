package com.pdi.pokeproject.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pdi.pokeproject.ManageThreads
import com.pdi.network.data.Pokemon
import com.pdi.pokeproject.domain.PokemonContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel (
    private val interactor: PokemonContract.Interactor,
    private val scheduler: ManageThreads
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val pokemonList = MutableLiveData<List<Pokemon>>()

    fun getPokemonsFromInteractor() {
        compositeDisposable.add(
            interactor.getPokemonsFromRepository()
                .subscribeOn(scheduler.io)
                .observeOn(scheduler.main)
                .subscribeBy (
                    onSuccess = {
                        pokemonList.value = it
                    },
                    onError = {
                        Log.e("Errouuuuu","Erro ao consultar a api: ${it.message}")
                    }
                )
        )
    }
}