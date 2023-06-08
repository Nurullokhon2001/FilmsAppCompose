package com.example.filmsappcompose.presentation.main_screen

import androidx.paging.PagingData
import com.example.filmsappcompose.domain.model.Movie
import kotlinx.coroutines.flow.Flow

sealed class MainScreenState {
    object Loading : MainScreenState()
    data class Error(val error: Throwable) : MainScreenState()
    data class Content(val data: Flow<PagingData<Movie>>) : MainScreenState()
}
