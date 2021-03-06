package com.pdi.pokemon_list.data.remote

import com.google.gson.annotations.SerializedName

data class TypeDetail (
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class Type (
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeDetail

)
