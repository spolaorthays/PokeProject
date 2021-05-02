package com.pdi.network.data

import com.google.gson.annotations.SerializedName

data class AbilityDetails(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class Ability (
    @SerializedName("ability")
    val ability: AbilityDetails,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)
