package com.pdi.pokeproject.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.pdi.network.PokemonService
import com.pdi.network.Retrofit
import com.pdi.network.data.Pokemon
import com.pdi.pokeproject.ManageThreads
import com.pdi.pokeproject.R
import com.pdi.pokeproject.databinding.ActivityMainBinding
import com.pdi.pokeproject.data.PokemonRepository
import com.pdi.pokeproject.domain.PokemonInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class DataBindingHelper: AppCompatActivity() {

    inline fun <reified T: ViewDataBinding> Activity.bind(@LayoutRes layout: Int, noinline block: T.() -> Unit): T {
        return DataBindingUtil.setContentView<T>(this, layout).apply {
            block.invoke(this)
        }
    }

}

class MainActivity : DataBindingHelper() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerPokemonAdapter

    private val viewModel: MainViewModel by lazy {
        MainViewModel(
            PokemonInteractor(
                PokemonRepository(
                    Retrofit.provideService(
                        PokemonService::class.java))),
            ManageThreads(AndroidSchedulers.mainThread(), Schedulers.io())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = bind(R.layout.activity_main) {
            viewmodel = viewModel
            lifecycleOwner = this@MainActivity
        }

        setupRecycler()
        setupViewModel()
    }

    private fun setupRecycler() {
        recyclerView = findViewById(R.id.recycler_pokemon_list)
        adapter = RecyclerPokemonAdapter()
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.getPokemonsFromInteractor()

        viewModel.pokemonList.observeForever { listPoke ->
            adapter.updatePokemons(listPoke)
        }
    }

}