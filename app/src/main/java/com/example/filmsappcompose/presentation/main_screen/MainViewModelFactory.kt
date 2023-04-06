package com.example.filmsappcompose.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmsappcompose.domain.use_case.*
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getPopularMoviesNetworkUseCase: GetPopularMoviesNetworkUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getPopularMoviesLocalUseCase: GetPopularMoviesLocalUseCase,
    private val insertMoviesUseCase: InsertMoviesUseCase,
    private val getGenreUseCase: GetGenreUseCase,
    private val filterByGenresUseCase: FilterByGenresUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(
            getPopularMoviesNetworkUseCase,
            searchMoviesUseCase,
            getPopularMoviesLocalUseCase,
            insertMoviesUseCase,
            getGenreUseCase,
            filterByGenresUseCase
        ) as T
    }
}