package com.example.filmsappcompose.presentation.main_screen

import com.example.filmsappcompose.domain.model.Movie

sealed class MainScreenState {
    object Loading : MainScreenState()
    data class Error(val error: Throwable) : MainScreenState()
    data class Content(val data: List<Movie>) : MainScreenState()
}
