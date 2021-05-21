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

interface PokemonService { //?limit={limit}&offset={offset}

    @GET("pokemon")  //TODO não esquecer limit (numero de elementos por pagina) e offset(quantos elementos pra frente ele busca), valores default são 20 em cada
    fun getPokemon(@Query("limit") limit: Int, @Query("offset") offset: Int): Single<PokemonResponse<Pokemon>>

    @GET
    fun getPokemonDetails(@Url url: String): Single<PokemonDetails>  //TODO https://pokeapi.co/api/v2/pokemon/1/ >> tem todos os detalhes do pokemon

    @GET("pokemon-form/{id}/")
    fun getPokemonForm(@Path("id") id: String) //TODO aqui tem o grass e poison usados na listagem e usa o id do pokemon para encontrar esse dado

    @GET
    fun getPokemonColor(@Url url: String): Single<PokemonSpecies> //TODO aqui irá pegar a cor para usar de fundo no card da lista de pokemons
}