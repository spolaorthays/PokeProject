package com.pdi.pokemon_list.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdi.pokemon_list.R
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.data.remote.Type
import com.pdi.pokemon_list.extension.changeTextColor
import com.pdi.share.extension.formatFirstLetterToUpperCase

class RecyclerTypePokemonAdapter(var pokemon: Pokemon) : RecyclerView.Adapter<RecyclerTypePokemonAdapter.Holder>() {

    var listTypePokemon = mutableListOf<Type>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(
                                R.layout.pokemon_type_item,
                                parent,
                                false
                        )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pokeType = listTypePokemon[position]
        holder.bind(pokeType)
    }

    override fun getItemCount(): Int = listTypePokemon.size

    fun updateTypePokemons(pokeTypeList: List<Type>) {
        listTypePokemon.addAll(pokeTypeList)
        notifyDataSetChanged()
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private var typePokemon = view.findViewById<TextView>(R.id.pokemon_type)
        private var linearLayout = view.findViewById<LinearLayout>(R.id.linear_layout)

        fun bind(type: Type) {
            typePokemon.text = type.type.name.formatFirstLetterToUpperCase()
            typePokemon.changeTextColor(pokemon, typePokemon.context, linearLayout)
        }
    }
}