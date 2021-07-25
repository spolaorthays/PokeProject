package com.pdi.pokemon_list.viewmodel

sealed class MainViewModelEvent {
    object Loading: MainViewModelEvent()
    object Loaded: MainViewModelEvent()
    object Error: MainViewModelEvent()
}
