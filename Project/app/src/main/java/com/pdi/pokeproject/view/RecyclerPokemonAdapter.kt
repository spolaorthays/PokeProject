package com.pdi.pokeproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdi.pokeproject.R
import com.pdi.network.data.Pokemon
import com.squareup.picasso.Picasso

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
        private var url = view.findViewById<TextView>(R.id.pokemon_url)
        private var image = view.findViewById<ImageView>(R.id.pokemon_image)
        private var type1 = view.findViewById<TextView>(R.id.pokemon_forma) //TODO forma é uma lista, então terei que receber uma lista aqui também

        fun bind(pokemon: Pokemon) {
            name.text = pokemon.name
            url.text = pokemon.url
            Picasso.get().load(pokemon.pokemonDetails.sprites.other.officialArtwotk.frontDefault).into(image)
            type1.text = pokemon.pokemonDetails.types[0].type.name
        }
    }
}