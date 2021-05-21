package com.pdi.pokemon_list.data.local

import com.pdi.pokemon_list.data.service.PokemonService
import com.pdi.pokemon_list.data.remote.PokemonDetails
import com.pdi.network.data.PokemonResponse
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.data.remote.PokemonSpecies
import com.pdi.pokemon_list.domain.PokemonContract
import io.reactivex.Single
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val service: PokemonService
) : PokemonContract.Repository {

    override fun getPokemonListFromService(limit: Int, offset: Int): Single<PokemonResponse<Pokemon>> =
        service.getPokemon(limit, offset)

    override fun getPokemonDetailsFromService(url: String): Single<PokemonDetails> =
        service.getPokemonDetails(url)

    override fun getPokemonColorFromService(url: String): Single<PokemonSpecies> =
        service.getPokemonColor(url)

}