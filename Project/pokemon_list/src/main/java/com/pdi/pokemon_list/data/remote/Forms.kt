package com.pdi.pokemon_list.data.remote

import com.google.gson.annotations.SerializedName

data class Forms (
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
)
