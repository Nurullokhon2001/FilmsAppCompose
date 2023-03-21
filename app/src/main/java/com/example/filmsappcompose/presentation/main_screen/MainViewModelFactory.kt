package com.example.filmsappcompose.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmsappcompose.domain.use_case.GetMoviesUseCase
import com.example.filmsappcompose.domain.use_case.GetPopularMoviesUseCase
import com.example.filmsappcompose.domain.use_case.InsertMoviesUseCase
import com.example.filmsappcompose.domain.use_case.SearchMoviesUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val insertMoviesUseCase: InsertMoviesUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(
            getPopularMoviesUseCase,
            searchMoviesUseCase,
            getMoviesUseCase,
            insertMoviesUseCase,
        ) as T
    }
}