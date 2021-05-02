package com.pdi.pokeproject.domain

import com.google.gson.GsonBuilder
import com.pdi.network.data.Pokemon
import com.pdi.network.data.PokemonDetails
import com.pdi.pokeproject.data.PokemonRepository
import io.reactivex.Single
import java.lang.reflect.Type

class PokemonInteractor(private val repository: PokemonRepository) : PokemonContract.Interactor{

    inline fun <reified T> parseArray(json: String, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson<T>(json, typeToken)
    }

    override fun getPokemonListFromRepository(): Single<List<Pokemon>> =
        repository.getPokemonListFromService().map { pokemonResponse ->
            pokemonResponse.results
        }

    override fun getPokemonDetailsFromRepository(url: String): Single<PokemonDetails> =
        repository.getPokemonDetailsFromService(url)

}