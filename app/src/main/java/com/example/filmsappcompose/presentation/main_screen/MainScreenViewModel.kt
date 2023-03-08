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
    application: Application,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) :
    AndroidViewModel(application) {

    private val _films = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val films = _films.asStateFlow()

//    private val _categories =
//        mutableStateOf(listOf("боевики", "драмы", "комедии", "артхаус", "мелодрамы", "комедии"))
//    val categories: State<List<String>> = _categories

    init {
        getPopularMovies()
    }

    fun onValueChange(newText: String) {
        viewModelScope.launch {
            if (newText.isNotBlank()) {
                searchMoviesUseCase.invoke(newText).collect {
                    it.doOnError { error ->
                        _films.emit(MainScreenState.Error(error))
                    }
                    it.doOnSuccess { data ->
                        _films.emit(MainScreenState.Content(data))
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
                    _films.emit(MainScreenState.Error(error))
                }
                it.doOnSuccess { data ->
                    _films.emit(MainScreenState.Content(data))
                }
            }
        }
    }
}