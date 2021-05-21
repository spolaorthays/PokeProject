package com.pdi.pokemon_list.data.remote

import com.google.gson.annotations.SerializedName

data class StatDetails (
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class Stats (
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: StatDetails
)
