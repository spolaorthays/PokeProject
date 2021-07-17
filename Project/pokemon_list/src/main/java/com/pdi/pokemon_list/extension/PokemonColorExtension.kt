package com.pdi.pokemon_list.extension

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.share.R

fun CardView.changeBackgroundColor(pokemon: Pokemon, context: Context, textView: TextView) {
    val colorName = pokemon.pokemonSpecies.color.name

    mapColorsBackground().entries.forEach {
        if (it.key == colorName) {
            this.setCardBackgroundColor(ContextCompat.getColor(context, it.value))
        }

        if (colorName == "white") {
            textView.setTextColor(ContextCompat.getColor(context, R.color.gray_500))
        }
    }
}

fun TextView.changeTextColor(pokemon: Pokemon, context: Context, layout: LinearLayout) {
    val colorName = pokemon.pokemonSpecies.color.name

    mapColorsText().entries.forEach {
        if (it.key == colorName) {
            this.setTextColor(ContextCompat.getColor(context, it.value))
        }

        if (colorName == "white") {
            layout.setBackgroundResource(R.drawable.elliptic_card_gray)
        }
    }
}

fun mapColorsBackground(): Map<String, Int> {
    return mapOf(
        Pair("red", R.color.red_500),
        Pair("yellow", R.color.yellow_500),
        Pair("green", R.color.green_500),
        Pair("blue", R.color.blue_500),
        Pair("black", R.color.black),
        Pair("white", R.color.white_card),
        Pair("brown", R.color.brown_500),
        Pair("pink", R.color.pink_500),
        Pair("gray", R.color.gray_500),
        Pair("purple", R.color.purple_500)
    )
}

fun mapColorsText(): Map<String, Int> {
    return mapOf(
        Pair("red", R.color.red_700),
        Pair("yellow", R.color.yellow_700),
        Pair("green", R.color.green_700),
        Pair("blue", R.color.blue_700),
        Pair("black", R.color.black),
        Pair("white", R.color.white_card),
        Pair("brown", R.color.brown_700),
        Pair("pink", R.color.pink_700),
        Pair("gray", R.color.gray_700),
        Pair("purple", R.color.purple_700)
    )
}