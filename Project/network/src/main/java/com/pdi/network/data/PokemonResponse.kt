package com.pdi.network.data

import com.google.gson.annotations.SerializedName

data class PokemonResponse<T> (
    //O SerializedName serve para mapear com o GSON
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<T>
)