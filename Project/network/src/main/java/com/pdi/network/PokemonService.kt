package com.pdi.network

import com.pdi.network.data.PokemonDetails
import com.pdi.network.data.PokemonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon")  //TODO não esquecer limit e offset, valores default são 20 em cada
    fun getPokemon(): Single<PokemonResponse>

    @GET
    fun getPokemonDetails(@Url url: String): Single<PokemonDetails>  //TODO https://pokeapi.co/api/v2/pokemon/1/ >> tem todos os detalhes do pokemon

    @GET("pokemon-form/{id}/")
    fun getPokemonForm(@Path("id") id: String) //TODO aqui tem o grass e poison usados na listagem e usa o id do pokemon para encontrar esse dado
}