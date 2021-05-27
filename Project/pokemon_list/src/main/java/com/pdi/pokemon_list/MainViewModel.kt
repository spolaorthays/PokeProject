package com.pdi.pokemon_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.data.remote.PokemonDetails
import com.pdi.pokemon_list.domain.PokemonContract
import com.pdi.share.ManageThreads
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: PokemonContract.Interactor,
    private val scheduler: ManageThreads
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val pokemonList = MutableLiveData<List<Pokemon>>()
    var pokemonComplete = mutableListOf<Pokemon>()
    //var colorAtual =

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
                                    pokemonComplete = mutableListOf()
                                    pokemonComplete.add(pokemon)
                                    pokemonList.value = pokemonComplete

                                    getPokemonColorFromInteractor(details, pokemon)
                                }, onError = {
                            Log.e("Errouuuuu", "Erro ao consultar a api: ${it.message}")
                        }
                        )
        )
    }

    private fun getPokemonColorFromInteractor(details: PokemonDetails, pokemon: Pokemon) {
        compositeDisposable.add(
                interactor.getPokemonColorRepository(details.species.url)
                        .subscribeOn(scheduler.io)
                        .observeOn(scheduler.main)
                        .subscribeBy(
                                onSuccess = { pokemonSpecies ->
                                    pokemon.pokemonSpecies = pokemonSpecies

                                    //TODO pensar agora se coloco como retorno aqui a cor, pois preciso passar esse dado para o recyclerView depois
                                }, onError = {
                            Log.e("Errouuuuu", "Erro ao consultar a api: ${it.message}")
                        }
                        )
        )
    }


}