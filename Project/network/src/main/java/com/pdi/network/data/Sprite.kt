package com.pdi.network.data

import com.google.gson.annotations.SerializedName

data class OfficialArtwork(
        @SerializedName("front_default")
        val frontDefault: String
)
data class SpriteDetails(
        @SerializedName("official-artwork")
        val officialArtwotk: OfficialArtwork
)

data class Sprite(
        @SerializedName("other")
        val other: SpriteDetails
)
