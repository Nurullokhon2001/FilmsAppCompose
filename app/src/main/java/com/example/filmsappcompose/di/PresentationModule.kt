package com.example.filmsappcompose.di

import com.example.filmsappcompose.domain.use_case.*
import com.example.filmsappcompose.presentation.main_screen.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainScreenViewModelFactory(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        searchMoviesUseCase: SearchMoviesUseCase,
        getMoviesUseCase: GetMoviesUseCase,
        insertMoviesUseCase: InsertMoviesUseCase,
        getGenreUseCase: GetGenreUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getPopularMoviesUseCase,
            searchMoviesUseCase,
            getMoviesUseCase,
            insertMoviesUseCase,
            getGenreUseCase
        )
    }
}