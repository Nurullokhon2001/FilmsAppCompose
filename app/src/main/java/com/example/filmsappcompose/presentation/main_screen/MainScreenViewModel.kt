package com.example.filmsappcompose.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsappcompose.domain.model.Genre
import com.example.filmsappcompose.domain.model.Movie
import com.example.filmsappcompose.domain.use_case.*
import com.example.filmsappcompose.utiils.doOnError
import com.example.filmsappcompose.utiils.doOnSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getPopularMoviesNetworkUseCase: GetPopularMoviesNetworkUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getPopularMoviesLocalUseCase: GetPopularMoviesLocalUseCase,
    private val insertMoviesUseCase: InsertMoviesUseCase,
    private val getGenreUseCase: GetGenreUseCase,
    private val filterByGenresUseCase: FilterByGenresUseCase,
) : ViewModel() {

    private val _movies = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    private val allMovies = MutableStateFlow<List<Movie>>(emptyList())
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

    fun filterByGenre(genreId: Int) {
        viewModelScope.launch {
            _movies.emit(
                MainScreenState.Content(
                    filterByGenresUseCase.invoke(
                        allMovies.value,
                        genreId
                    )
                )
            )
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesNetworkUseCase.invoke().collect { remote ->
                remote.doOnError { error ->
                    getPopularMoviesLocalUseCase.invoke().collect { local ->
                        local.doOnSuccess { data ->
                            if (data.isEmpty()) {
                                _movies.emit(MainScreenState.Error(error))
                            }
                            _movies.emit(MainScreenState.Content(data))
                            allMovies.emit(data)
                        }
                    }
                }
                remote.doOnSuccess { data ->
                    _movies.emit(MainScreenState.Content(data))
                    insertMoviesUseCase.invoke(data)
                    allMovies.emit(data)
                }
            }
        }
    }
}