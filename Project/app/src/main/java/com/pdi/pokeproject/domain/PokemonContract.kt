package com.pdi.pokeproject.domain

import com.pdi.network.data.Pokemon
import com.pdi.network.data.PokemonDetails
import com.pdi.network.data.PokemonResponse
import com.pdi.network.data.PokemonSpecies
import io.reactivex.Single

interface PokemonContract {

    interface Interactor {
        fun getPokemonListFromRepository(limit: Int, offset: Int): Single<List<Pokemon>>
        fun getPokemonDetailsFromRepository(url: String): Single<PokemonDetails>
        fun getPokemonColorRepository(url: String): Single<PokemonSpecies>
    }

    interface Repository {
        fun getPokemonListFromService(limit: Int, offset: Int): Single<PokemonResponse>
        fun getPokemonDetailsFromService(url: String): Single<PokemonDetails>
        fun getPokemonColorFromService(url: String): Single<PokemonSpecies>
    }

}