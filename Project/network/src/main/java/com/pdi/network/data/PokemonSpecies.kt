package com.pdi.network.data

import com.google.gson.annotations.SerializedName

data class ColorDetails(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
)

data class PokemonSpecies (
        @SerializedName("color")
        val color: ColorDetails
)