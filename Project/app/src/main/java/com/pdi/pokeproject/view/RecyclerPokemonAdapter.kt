package com.pdi.pokeproject.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.pdi.pokeproject.R
import com.pdi.network.data.Pokemon
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class RecyclerPokemonAdapter: RecyclerView.Adapter<RecyclerPokemonAdapter.PokeHolder>() {

    var listPokemon = mutableListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeHolder {
        return PokeHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.pokemon_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: PokeHolder, position: Int) {
        val pokemon = listPokemon[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = listPokemon.size

    fun updatePokemons(pokeList: List<Pokemon>) {
        listPokemon.addAll(pokeList)
        notifyDataSetChanged()
    }

    inner class PokeHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var name = view.findViewById<TextView>(R.id.pokemon_name)
        private var image = view.findViewById<ImageView>(R.id.pokemon_image)
        private var recyclerView = view.findViewById<RecyclerView>(R.id.recycler_types)
        private lateinit var adapter: RecyclerTypePokemonAdapter
        private var card = view.findViewById<CardView>(R.id.pokemon_card)

        fun bind(pokemon: Pokemon) {
            setupRecycler()
            name.text = pokemon.name
            Picasso.get().load(pokemon.pokemonDetails.sprites.other.officialArtwotk.frontDefault).into(image)
//            Picasso.get().load(pokemon.pokemonDetails.sprites.other.officialArtwotk.frontDefault).into(object : Target { //TODO está deixando o carrgamento bem lento
//                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
//                    image.setImageBitmap(bitmap)
//                    bitmap?.let {
//                        Palette.Builder(bitmap).generate { palette ->
//                            palette?.vibrantSwatch?.rgb.let { paletteColor ->
//                                paletteColor?.let { color ->
//                                    card.setCardBackgroundColor(color)
//                                }
//                            }
//                        }
//                    }
//                }
//
//                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
//                    image.setImageDrawable(errorDrawable)
//                }
//
//                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
//                    image.setImageDrawable(placeHolderDrawable)
//                }
//
//            })
            adapter.updateTypePokemons(pokemon.pokemonDetails.types)
        }

        fun setupRecycler() {
            adapter = RecyclerTypePokemonAdapter()
            recyclerView.adapter = adapter
        }
    }
}