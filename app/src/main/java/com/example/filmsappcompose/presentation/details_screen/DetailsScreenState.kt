package com.example.filmsappcompose.presentation.details_screen

import com.example.filmsappcompose.domain.model.Movie

sealed class DetailsScreenState {
    object Loading : DetailsScreenState()
    data class Error(val error: Throwable) : DetailsScreenState()
    data class Content(val data: List<Movie>) : DetailsScreenState()
}