package com.pdi.network.data

import com.google.gson.annotations.SerializedName

data class PokemonResponse (
    @SerializedName("count") //TODO tem que colocar isso para mapear com o GSON
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<Pokemon>
)