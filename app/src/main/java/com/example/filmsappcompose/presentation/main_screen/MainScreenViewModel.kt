package com.example.filmsappcompose.presentation.main_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsappcompose.domain.use_case.GetPopularMoviesUseCase
import com.example.filmsappcompose.domain.use_case.SearchMoviesUseCase
import com.example.filmsappcompose.utiils.doOnError
import com.example.filmsappcompose.utiils.doOnSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    application: Application,
) :
    AndroidViewModel(application) {

    private val _movies = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val movie = _movies.asStateFlow()

    init {
        getPopularMovies()
    }

    fun onValueChange(newText: String) {
        viewModelScope.launch {
            if (newText.isNotBlank()) {
                searchMoviesUseCase.invoke(newText).collect {
                    it.doOnError { error ->
                        _movies.emit(MainScreenState.Error(error))
                    }
                    it.doOnSuccess { data ->
                        _movies.emit(MainScreenState.Content(data))
                    }
                }
            } else {
                getPopularMovies()
            }
        }
    }

    fun leadingIconClicked() {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.invoke().collect {
                it.doOnError { error ->
                    _movies.emit(MainScreenState.Error(error))
                }
                it.doOnSuccess { data ->
                    _movies.emit(MainScreenState.Content(data))
                }
            }
        }
    }
}