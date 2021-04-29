package com.pdi.pokeproject.domain

import com.pdi.network.data.Pokemon
import com.pdi.network.data.PokemonResponse
import io.reactivex.Single

interface PokemonContract {

    interface Interactor {
        fun getPokemonsFromRepository(): Single<List<Pokemon>>
    }

    interface Repository {
        fun getPokemonsFromService(): Single<PokemonResponse>
    }

}