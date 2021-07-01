package com.pdi.pokemon_list.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pdi.pokemon_list.R
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.share.extension.formatFirstLetterToUpperCase
import com.squareup.picasso.Picasso

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
            setColorCard(pokemon)

            name.text = pokemon.name.formatFirstLetterToUpperCase()
            Picasso.get().load(pokemon.pokemonDetails.sprites.other.officialArtwotk.frontDefault).into(image) //TODO fazer extension para um imageView
            adapter.updateTypePokemons(pokemon.pokemonDetails.types)
        }

        fun setColorCard(pokemon: Pokemon) {
            val pokemonColor = pokemon.pokemonSpecies.color.name
            when (pokemonColor) {
                "red" -> card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.red)) //#FF0000
                "yellow" -> card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.yellow)) //#FFFF00
                "green" -> card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.green)) //#008000
                "blue" -> card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.blue)) //#0000FF
                "black" -> card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.black))
                "white" -> {
                    card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.white_card))
                    name.setTextColor(ContextCompat.getColor(name.context, R.color.gray))
                }
                "brown" -> card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.brown))
                "pink" -> card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.pink)) //#FF748C
                "gray" -> card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.gray))
                "purple" -> card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.purple))
                else -> {
                    Log.d("COR NÃO MAPEADA", "a cor não está na lista mapeada e seu nome é: $pokemonColor")
                    card.setCardBackgroundColor(ContextCompat.getColor(card.context, R.color.red))
                }
            }
        }

        fun setupRecycler(pokemon: Pokemon) {
            adapter = RecyclerTypePokemonAdapter(pokemon)
            recyclerView.adapter = adapter
        }
    }
}