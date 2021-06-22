package com.pdi.share.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

//TODO Tentar fazer um base dos recycler para aceitar mais facilmente a troca de cores do cardview e da fonte
abstract class BaseRecyclerView<T>(var context: Context,
                                   @LayoutRes val layoutId: Int,
                                   inline val bind: (item: T, holder: BaseViewHolder, itemCount: Int) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() { //TODO ListAdapter fornece o DiffUtils que abre espaço para animações

    private var genericList: List<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layout = LayoutInflater
                .from(parent.context)
                .inflate(
                        layoutId,
                        parent,
                        false
                )
        return BaseViewHolder(layout as ViewGroup)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(getItem(position), holder, itemCount)
    }

    override fun getItemCount(): Int = genericList.size

    fun addItems(items: List<T>) {
        genericList = items
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return genericList[position]
    }
}