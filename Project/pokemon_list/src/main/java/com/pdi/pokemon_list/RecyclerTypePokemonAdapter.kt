package com.pdi.pokemon_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdi.pokemon_list.data.remote.Type

class RecyclerTypePokemonAdapter : RecyclerView.Adapter<RecyclerTypePokemonAdapter.Holder>() {

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

        fun bind(type: Type) {
            typePokemon.text = type.type.name
        }
    }
}