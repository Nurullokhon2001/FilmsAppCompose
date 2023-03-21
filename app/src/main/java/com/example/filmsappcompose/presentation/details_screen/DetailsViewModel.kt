package com.example.filmsappcompose.presentation.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.filmsappcompose.domain.use_case.GetMovieActorsUseCase
import com.example.filmsappcompose.domain.use_case.GetMovieDetailsUseCase
import com.example.filmsappcompose.utiils.doOnError
import com.example.filmsappcompose.utiils.doOnSuccess
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @AssistedInject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieActorsUseCase: GetMovieActorsUseCase,
    @Assisted private val movieId: Int
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<DetailsScreenState>(DetailsScreenState.Loading)
    val movieDetails: StateFlow<DetailsScreenState> get() = _movieDetails

    class Factory(
        private val assistedFactory: UserViewModelAssistedFactory,
        private val name: Int,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(name) as T
        }
    }

    init {
        viewModelScope.launch {
            getMovieDetailsUseCase.invoke(movieId).collect {
                it.doOnError { error ->
                    _movieDetails.value = DetailsScreenState.Error(error)
                }
                it.doOnSuccess { content ->
                    val actors = getMovieActorsUseCase.invoke(movieId)
                    _movieDetails.value = DetailsScreenState.Content(content.copy(actors = actors))
                }
            }
        }
    }
}