package com.example.filmsappcompose.main_screen

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.filmsappcompose.main_screen.domain.Film
import com.example.filmsappcompose.utiils.toList

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val _films = mutableStateOf(application.toList())
    val films: State<List<Film>> = _films

    private val _categories =
        mutableStateOf(listOf("боевики", "драмы", "комедии", "артхаус", "мелодрамы", "комедии"))
    val categories: State<List<String>> = _categories
}