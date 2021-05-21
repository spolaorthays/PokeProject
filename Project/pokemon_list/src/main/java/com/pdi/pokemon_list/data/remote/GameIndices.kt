package com.pdi.pokemon_list.data.remote

import com.google.gson.annotations.SerializedName

data class Version(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
)

data class GameIndices(
        @SerializedName("game_index")
        val gameIndex: Int,
        @SerializedName("version")
        val version: Version
)
