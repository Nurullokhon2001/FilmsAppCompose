package com.example.filmsappcompose.presentation.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

class Factory(
    private val assistedFactory: UserViewModelAssistedFactory,
    private val name: Int,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(name) as T
    }
}

@AssistedFactory
interface UserViewModelAssistedFactory {
    fun create(name: Int): DetailsViewModel
}