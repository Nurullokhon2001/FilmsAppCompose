package com.example.filmsappcompose.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsappcompose.domain.model.Genre
import com.example.filmsappcompose.domain.use_case.*
import com.example.filmsappcompose.utiils.doOnError
import com.example.filmsappcompose.utiils.doOnSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getGenreUseCase: GetGenreUseCase,
    private val getPopularMoviesPaging: GetPopularMoviesPaging,
) : ViewModel() {

    private val _movies = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val movie = _movies.asStateFlow()

    private val _genre = MutableStateFlow(emptyList<Genre>())
    val genre = _genre.asStateFlow()

    init {
        viewModelScope.launch {
            getGenreUseCase.invoke().collect {
                it.doOnSuccess { genres ->
                    _genre.value = genres
                }
                it.doOnError {
                    _genre.value = emptyList()
                }
            }
            getPopularMovies()
        }
    }

    fun onValueChange(newText: String) {
        viewModelScope.launch {
            if (newText.isNotBlank()) {
                getPopularMovies(newText)
            }
        }
    }

    fun leadingIconClicked() {
        viewModelScope.launch {
            getGenreUseCase.invoke().collect {
                it.doOnSuccess { genres ->
                    _genre.value = genres
                }
                it.doOnError {
                    _genre.value = emptyList()
                }
            }
        }
        getPopularMovies()
    }

    private fun getPopularMovies(query: String = "") {
        viewModelScope.launch {
            getPopularMoviesPaging.invoke(query).collect {
                it.doOnSuccess { resourceData ->
                    _movies.emit(MainScreenState.Content(resourceData))
                }
            }
        }
    }
}