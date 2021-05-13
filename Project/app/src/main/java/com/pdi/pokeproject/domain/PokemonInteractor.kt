package com.pdi.pokeproject.domain

import com.pdi.network.data.Pokemon
import com.pdi.network.data.PokemonDetails
import com.pdi.network.data.PokemonSpecies
import com.pdi.pokeproject.data.PokemonRepository
import io.reactivex.Single

class PokemonInteractor(private val repository: PokemonRepository) : PokemonContract.Interactor{

    override fun getPokemonListFromRepository(limit: Int, offset: Int): Single<List<Pokemon>> =
        repository.getPokemonListFromService(limit, offset).map { pokemonResponse ->
            pokemonResponse.results
        }

    override fun getPokemonDetailsFromRepository(url: String): Single<PokemonDetails> =
        repository.getPokemonDetailsFromService(url)

    override fun getPokemonColorRepository(url: String): Single<PokemonSpecies> =
        repository.getPokemonColorFromService(url)

}