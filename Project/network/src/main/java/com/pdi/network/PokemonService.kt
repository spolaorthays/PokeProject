package com.pdi.network

import com.pdi.network.data.PokemonResponse
import io.reactivex.Single
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon")  //TODO não esquecer limit e offset, valores default são 20 em cada
    fun getPokemon(): Single<PokemonResponse>
}