package com.example.filmsappcompose.main_screen

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmsappcompose.utiils.Resource
import com.example.filmsappcompose.utiils.getMokData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val _films = MutableStateFlow<Resource>(Resource.Loading)
    val films: StateFlow<Resource> = _films

    private val _categories =
        mutableStateOf(listOf("боевики", "драмы", "комедии", "артхаус", "мелодрамы", "комедии"))
    val categories: State<List<String>> = _categories

    init {
        viewModelScope.launch {
            _films.value = Resource.Success(application.applicationContext.getMokData())
        }
    }
}