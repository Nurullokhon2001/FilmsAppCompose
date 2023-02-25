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

    private var mokData = application.applicationContext.getMokData()

    init {
        viewModelScope.launch {
            _films.value = Resource.Success(mokData)
        }
    }

    fun onValueChange(newText: String, category: String) {
        _films.value = Resource.Loading
        _films.value = Resource.Success(mokData.filter {
            (it.name.contains(newText, true) ||
                    it.description.contains(newText, true)) &&
                    it.category.contains(category, true)
        })
    }

    fun leadingIconClicked() {
        _films.value = Resource.Loading
        _films.value = Resource.Success(mokData)
    }
}