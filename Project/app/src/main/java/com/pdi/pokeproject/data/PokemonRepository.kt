package com.pdi.pokeproject.data

import com.pdi.network.PokemonService
import com.pdi.network.data.Pokemon
import com.pdi.network.data.PokemonDetails
import com.pdi.network.data.PokemonResponse
import com.pdi.network.data.PokemonSpecies
import com.pdi.pokeproject.domain.PokemonContract
import io.reactivex.Single

class PokemonRepository(private val service: PokemonService) : PokemonContract.Repository {

    override fun getPokemonListFromService(limit: Int, offset: Int): Single<PokemonResponse> =
        service.getPokemon(limit, offset)

    override fun getPokemonDetailsFromService(url: String): Single<PokemonDetails> =
        service.getPokemonDetails(url)

    override fun getPokemonColorFromService(url: String): Single<PokemonSpecies> =
        service.getPokemonColor(url)

}