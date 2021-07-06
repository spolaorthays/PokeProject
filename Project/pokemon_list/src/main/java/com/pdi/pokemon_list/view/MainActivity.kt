package com.pdi.pokemon_list.view

import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.pdi.pokemon_list.R
import com.pdi.pokemon_list.databinding.ActivityMainBinding
import com.pdi.pokemon_list.viewmodel.MainViewModel
import com.pdi.pokemon_list.viewmodel.MainViewModelEvent
import com.pdi.share.extension.observeValue
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideStatusBar()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@MainActivity
        }

        setupRecycler()
        setupViewModel()
        exitApp()
        showOptions()
        watchEvent()
    }

    private fun exitApp() {
        val btBack = binding.toolbar.findViewById<ImageView>(R.id.button_close)
        btBack.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle(R.string.alert_title)
                    .setMessage(R.string.alert_message_exit)
                    .setCancelable(false)
                    .setPositiveButton(R.string.alert_positive) { dialog, which ->
                        dialog.dismiss()
                        finish()
                    }.setNegativeButton(R.string.alert_negative) { dialog, which ->
                        dialog.dismiss()
                    }.show()
        }
    }

    private fun showOptions() {
        val btMenu = binding.toolbar.findViewById<ImageView>(R.id.button_options)
        btMenu.setOnClickListener {
            Snackbar.make(btMenu, R.string.message_debit_function, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    private fun setupRecycler() { //TODO aqui eu deveria estar pegando pelo binding e n referenciand o id
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

                    if (recyclerView.canScrollVertically(1).not() && viewModel.loading.value == false) {
                        viewModel.getPokemonsFromInteractor(LIMIT, offset+ LIMIT)
                        offset += LIMIT
                    }

                }
            })
        }
    }

    fun watchEvent() {
        observeValue(viewModel.eventState) { event ->
            when (event) {
                is MainViewModelEvent.Loading -> showLoading()
            }
        }
    }

    fun showLoading() {
        val progress = findViewById<CircularProgressIndicator>(R.id.progress_circular)
        progress.show()
    }

}