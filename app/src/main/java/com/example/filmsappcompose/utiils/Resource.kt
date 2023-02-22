package com.example.filmsappcompose.utiils

import com.example.filmsappcompose.main_screen.domain.Film

sealed class Resource {
    object Loading : Resource()
    data class Success(val data: List<Film>) : Resource()
    data class Error(val throwable: Throwable) : Resource()
}