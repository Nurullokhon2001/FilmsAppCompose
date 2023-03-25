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
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val insertMoviesUseCase: InsertMoviesUseCase,
    private val getGenreUseCase: GetGenreUseCase,
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
            getPopularMoviesUseCase.invoke().collect { remote ->
                remote.doOnError { error ->
                    getMoviesUseCase.invoke().collect { local ->
                        local.doOnSuccess { data ->
                            _movies.emit(MainScreenState.Content(data))
                            if (data.isEmpty()) {
                                _movies.emit(MainScreenState.Error(error))
                            }
                        }
                    }
                }
                remote.doOnSuccess { data ->
                    _movies.emit(MainScreenState.Content(data))
                    insertMoviesUseCase.invoke(data)
                }
            }
        }
    }
}