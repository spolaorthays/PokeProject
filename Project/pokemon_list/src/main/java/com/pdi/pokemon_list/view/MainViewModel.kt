package com.pdi.pokemon_list.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.domain.PokemonContract
import com.pdi.share.AppSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: PokemonContract.Interactor,
    private val scheduler: AppSchedulers
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val pokemonList = MutableLiveData<List<Pokemon>>()
    var pokemonComplete = mutableListOf<Pokemon>()

    fun getPokemonsFromInteractor(limit: Int, offset: Int) {
        compositeDisposable.add(
                interactor.getPokemonListFromRepository(limit, offset)
                        .subscribeOn(scheduler.io)
                        .observeOn(scheduler.main)
                        .subscribeBy(
                                onSuccess = {
                                    MainActivity.isLoading = false
                                    it.forEach { pokemon ->
                                        getPokemonDetailFromInteractor(pokemon)
                                    }
                                },
                                onError = {
                                    Log.e("Errouuuuu", "Erro ao consultar a api: ${it.message}")
                                }
                        )
        )
    }

    private fun getPokemonDetailFromInteractor(pokemon: Pokemon) {
        compositeDisposable.add(
                interactor.getPokemonDetailsFromRepository(pokemon.url)
                        .subscribeOn(scheduler.io)
                        .observeOn(scheduler.main)
                        .subscribeBy(
                                onSuccess = { details ->
                                    pokemon.pokemonDetails = details

                                    getPokemonColorFromInteractor(pokemon)
                                }, onError = {
                            Log.e("Errouuuuu", "Erro ao consultar a api: ${it.message}")
                        }
                        )
        )
    }

    //Como são requisições encadeadas, a lista tem que ser setada na última requisição
    private fun getPokemonColorFromInteractor(pokemon: Pokemon) {
        compositeDisposable.add(
                interactor.getPokemonColorRepository(pokemon.pokemonDetails.species.url)
                        .subscribeOn(scheduler.io)
                        .observeOn(scheduler.main)
                        .subscribeBy(
                                onSuccess = { pokemonSpecies ->
                                    pokemon.pokemonSpecies = pokemonSpecies
                                    pokemonComplete = mutableListOf()
                                    pokemonComplete.add(pokemon)
                                    pokemonList.value = pokemonComplete
                                }, onError = {
                            Log.e("Errouuuuu", "Erro ao consultar a api: ${it.message}")
                        }
                        )
        )
    }

}