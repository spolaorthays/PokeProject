package com.pdi.pokemon_list.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pdi.pokemon_list.R
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.extension.changeBackgroundColor
import com.pdi.share.extension.formatFirstLetterToUpperCase
import com.pdi.share.extension.loadImage

class RecyclerPokemonAdapter: RecyclerView.Adapter<RecyclerPokemonAdapter.PokeHolder>() { //TODO olhar viewBinding p/ prox etapa

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
            setupRecycler(pokemon)

            name.text = pokemon.name.formatFirstLetterToUpperCase()
            ContextCompat.getDrawable(image.context, R.drawable.loading)?.let {
                image.loadImage(
                    pokemon.pokemonDetails.sprites.other.officialArtwotk.frontDefault, it
                )
            }
            card.changeBackgroundColor(pokemon, card.context, name)
            adapter.updateTypePokemons(pokemon.pokemonDetails.types)
        }

        fun setupRecycler(pokemon: Pokemon) {
            adapter = RecyclerTypePokemonAdapter(pokemon)
            recyclerView.adapter = adapter
        }
    }
}