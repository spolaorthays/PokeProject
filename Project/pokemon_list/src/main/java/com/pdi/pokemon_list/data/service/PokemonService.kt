package com.pdi.pokemon_list.data.service

import com.pdi.network.data.PokemonResponse
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.data.remote.PokemonDetails
import com.pdi.pokemon_list.data.remote.PokemonSpecies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon")
    fun getPokemon(@Query("limit") limit: Int, @Query("offset") offset: Int): Single<PokemonResponse<Pokemon>>

    @GET
    fun getPokemonDetails(@Url url: String): Single<PokemonDetails>

    @GET
    fun getPokemonColor(@Url url: String): Single<PokemonSpecies>
}