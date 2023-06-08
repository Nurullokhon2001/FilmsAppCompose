package com.example.filmsappcompose.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmsappcompose.domain.use_case.*
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getGenreUseCase: GetGenreUseCase,
    private val getPopularMoviesPaging: GetPopularMoviesPaging,
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(
            getGenreUseCase,
            getPopularMoviesPaging
        ) as T
    }
}