package com.pdi.pokemon_list.domain

import com.pdi.pokemon_list.data.local.PokemonRepository
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.data.remote.PokemonDetails
import com.pdi.pokemon_list.data.remote.PokemonSpecies
import io.reactivex.Single
import javax.inject.Inject

class PokemonInteractor @Inject constructor(
    private val repository: PokemonRepository
) : PokemonContract.Interactor {

    override fun getPokemonListFromRepository(limit: Int, offset: Int): Single<List<Pokemon>> =
        repository.getPokemonListFromService(limit, offset).map { pokemonResponse ->
            pokemonResponse.results
        }

    override fun getPokemonDetailsFromRepository(url: String): Single<PokemonDetails> =
        repository.getPokemonDetailsFromService(url)

    override fun getPokemonColorRepository(url: String): Single<PokemonSpecies> =
        repository.getPokemonColorFromService(url)

}