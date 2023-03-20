package com.example.filmsappcompose.presentation.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsappcompose.domain.use_case.GetMovieActorsUseCase
import com.example.filmsappcompose.domain.use_case.GetMovieDetailsUseCase
import com.example.filmsappcompose.utiils.doOnError
import com.example.filmsappcompose.utiils.doOnSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieActorsUseCase: GetMovieActorsUseCase,
    movieId: Int
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<DetailsScreenState>(DetailsScreenState.Loading)
    val movieDetails: StateFlow<DetailsScreenState> get() = _movieDetails

    init {
        viewModelScope.launch {
            val actors = getMovieActorsUseCase.invoke(movieId)

            getMovieDetailsUseCase.invoke(movieId).collect {
                it.doOnError { error ->
                    _movieDetails.value = DetailsScreenState.Error(error)
                }
                it.doOnSuccess { content ->
                    _movieDetails.value = DetailsScreenState.Content(content.copy(actors = actors))
                }
            }
        }
    }
}