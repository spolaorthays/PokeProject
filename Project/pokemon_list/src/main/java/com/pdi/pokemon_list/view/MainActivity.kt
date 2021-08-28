package com.pdi.pokemon_list.view

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pdi.pokemon_list.R
import com.pdi.pokemon_list.databinding.ActivityMainBinding
import com.pdi.pokemon_list.viewmodel.MainViewModel
import com.pdi.pokemon_list.viewmodel.MainViewModelEvent
import com.pdi.share.extension.observeValue
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerPokemonAdapter
    private lateinit var alertDialog: AlertDialog.Builder

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideStatusBar()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@MainActivity
        }

        setupAlert()
        setupRecycler()
        exitApp()
        showOptions()
        watchEvent()
    }

    private fun setupAlert() {
        alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle(R.string.alert_title)
            setMessage(R.string.alert_message_exit)
            setCancelable(false)
        }
    }

    private fun exitApp() {
        val btBack = binding.toolbar.findViewById<ImageView>(R.id.button_close)
        btBack.setOnClickListener {
            alertDialog
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

    private fun setupRecycler() {
        adapter = RecyclerPokemonAdapter()
        binding.recyclerPokemonList.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerPokemonList.setHasFixedSize(true)
        binding.recyclerPokemonList.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.pokemonList.observeForever { listPoke ->
            adapter.updatePokemons(listPoke)

            binding.recyclerPokemonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (recyclerView.canScrollVertically(1).not() && viewModel.loading.value == false) {
                        viewModel.updateOffset.value = true
                        viewModel.getPokemonsFromInteractor()
                    }
                    viewModel.updateOffset.value = false
                }
            })
        }
    }

    private fun watchEvent() {
        observeValue(viewModel.eventState) { event ->
            when (event) {
                is MainViewModelEvent.Loading -> loadingVisibility(View.VISIBLE, true)
                is MainViewModelEvent.Loaded -> loadingVisibility(View.GONE, false)
                is MainViewModelEvent.Error -> {
                    errorApi()
                    callTryAgain()
                }
                is MainViewModelEvent.Success -> setupViewModel()
                is MainViewModelEvent.Empty -> showMessageEmptyList()
            }
        }
    }

    private fun loadingVisibility(progressVisibility: Int, showProgress: Boolean) {
        viewModel.progressVisibility.value = progressVisibility
        viewModel.tryAgainVisibility.value = View.GONE
        if (showProgress) {
            binding.progressCircular.show()
        } else {
            binding.progressCircular.hide()
        }
    }

    private fun errorApi() {
        viewModel.progressVisibility.value = View.VISIBLE
        Toast.makeText(this, getString(R.string.verify_connection), Toast.LENGTH_LONG).show()
    }

    private fun callTryAgain() {
        viewModel.tryAgainVisibility.value = View.VISIBLE
        binding.tryAgain.setOnClickListener {
            setupViewModel()
            viewModel.tryAgainVisibility.value = View.GONE
        }
    }

    private fun showMessageEmptyList() {
        viewModel.updateOffset.value = false
        Toast.makeText(this, getString(R.string.message_finish_list), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        alertDialog.setOnDismissListener {
            it.dismiss()
        }
    }

}