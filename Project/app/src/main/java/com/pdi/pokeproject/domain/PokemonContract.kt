package com.pdi.pokeproject.domain

import com.pdi.network.data.Pokemon
import com.pdi.network.data.PokemonDetails
import com.pdi.network.data.PokemonResponse
import io.reactivex.Single

interface PokemonContract {

    interface Interactor {
        fun getPokemonListFromRepository(): Single<List<Pokemon>>
        fun getPokemonDetailsFromRepository(url: String): Single<PokemonDetails>
    }

    interface Repository {
        fun getPokemonListFromService(): Single<PokemonResponse>
        fun getPokemonDetailsFromService(url: String): Single<PokemonDetails>
    }

}