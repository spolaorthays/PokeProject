package com.pdi.pokemon_list.view

import android.app.Activity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdi.pokemon_list.R
import com.pdi.pokemon_list.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class DataBindingHelper: AppCompatActivity() {

    inline fun <reified T : ViewDataBinding> Activity.bind(@LayoutRes layout: Int, noinline block: T.() -> Unit): T {
        return DataBindingUtil.setContentView<T>(this, layout).apply {
            block.invoke(this)
        }
    }

}

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerPokemonAdapter
    private var offset = 0

    @Inject
    lateinit var viewModel: MainViewModel

    companion object {
        const val LIMIT = 10
        var isLoading = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@MainActivity
        }

        setupRecycler()
        setupViewModel()
    }

    private fun setupRecycler() {
        recyclerView = findViewById(R.id.recycler_pokemon_list)
        adapter = RecyclerPokemonAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.getPokemonsFromInteractor(LIMIT, offset)

        viewModel.pokemonList.observeForever { listPoke ->
            adapter.updatePokemons(listPoke)

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (recyclerView.canScrollVertically(1).not() && isLoading.not()) {
                        isLoading = true
                        viewModel.getPokemonsFromInteractor(LIMIT, offset+ LIMIT)
                        offset += LIMIT
                    }

                }
            })
        }
    }

}