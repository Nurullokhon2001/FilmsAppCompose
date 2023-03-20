package com.example.filmsappcompose.presentation.details_screen

import com.example.filmsappcompose.domain.model.MovieDetails

sealed class DetailsScreenState {
    object Loading : DetailsScreenState()
    data class Error(val error: Throwable) : DetailsScreenState()
    data class Content(val data: MovieDetails) : DetailsScreenState()
}