package com.example.filmsappcompose.di

import com.example.filmsappcompose.domain.use_case.*
import com.example.filmsappcompose.presentation.main_screen.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainScreenViewModelFactory(
        getGenreUseCase: GetGenreUseCase,
        getPopularMoviesPaging: GetPopularMoviesPaging,
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getGenreUseCase,
            getPopularMoviesPaging
        )
    }
}