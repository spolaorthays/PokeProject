package com.pdi.pokeproject.data

import com.pdi.network.PokemonService
import com.pdi.network.data.PokemonResponse
import com.pdi.pokeproject.domain.PokemonContract
import io.reactivex.Single

class PokemonRepository(private val service: PokemonService) : PokemonContract.Repository {

    override fun getPokemonsFromService(): Single<PokemonResponse> {
        return service.getPokemon()
    }
}