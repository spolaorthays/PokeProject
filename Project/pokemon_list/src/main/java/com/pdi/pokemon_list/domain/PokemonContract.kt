package com.pdi.pokemon_list.domain

import com.pdi.network.data.PokemonResponse
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.data.remote.PokemonDetails
import com.pdi.pokemon_list.data.remote.PokemonSpecies
import io.reactivex.Single

interface PokemonContract {

    interface Interactor {
        fun getPokemonListFromRepository(limit: Int, offset: Int): Single<List<Pokemon>>
        fun getPokemonDetailsFromRepository(url: String): Single<PokemonDetails>
        fun getPokemonColorRepository(url: String): Single<PokemonSpecies>
    }

    interface Repository {
        fun getPokemonListFromService(limit: Int, offset: Int): Single<PokemonResponse<Pokemon>>
        fun getPokemonDetailsFromService(url: String): Single<PokemonDetails>
        fun getPokemonColorFromService(url: String): Single<PokemonSpecies>
    }

}