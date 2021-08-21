package com.pdi.pokemon_list.viewmodel

import android.text.TextUtils.isEmpty
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.domain.PokemonContract
import com.pdi.share.AppSchedulers
import com.pdi.share.utils.MutableLiveDataNotIsNull
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
    var loading = MutableLiveData<Boolean>()
    val eventState = MutableLiveData<MainViewModelEvent>()
    val progressVisibility = MutableLiveData<Int>()
    val tryAgainVisibility = MutableLiveData<Int>()
    val limit = MutableLiveDataNotIsNull(10)
    val offset = MutableLiveDataNotIsNull(0)
    val updateOffset = MutableLiveData<Boolean>()

    init {
        getPokemonsFromInteractor(limit.value, offset.value)
    }

    fun getPokemonsFromInteractor(limit: Int, offset: Int) {
        compositeDisposable.add(
            interactor.getPokemonListFromRepository(limit, offset)
                .subscribeOn(scheduler.io)
                .observeOn(scheduler.main)
                .doOnSubscribe {
                    loading.value = true
                    emitEvent(MainViewModelEvent.Loading)
                }
                .doAfterSuccess {
                    loading.value = false
                    emitEvent(MainViewModelEvent.Loaded)
                }
                .subscribeBy(
                    onSuccess = { successResults(it) },
                    onError = { emitEvent(MainViewModelEvent.Error) }
                )
        )
    }

    fun successResults(list: List<Pokemon>) {
        if (list.isEmpty()) {
            emitEvent(MainViewModelEvent.Empty)
        } else {
            list.forEach { pokemon ->
                getPokemonDetailFromInteractor(pokemon)
            }
            emitEvent(MainViewModelEvent.Success)
        }
    }

    fun updateOffsetValue() {
        if (updateOffset.value == true) {
            offset.value += limit.value
        }
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
                                }, onError = {}
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
                                    pokemonComplete.add(pokemon)
                                    pokemonList.value = pokemonComplete
                                }, onError = {}
                        )
        )
    }

    private fun emitEvent(state: MainViewModelEvent) {
        eventState.value = state
    }

}