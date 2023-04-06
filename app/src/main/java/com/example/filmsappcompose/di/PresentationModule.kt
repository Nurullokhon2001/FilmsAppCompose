package com.example.filmsappcompose.di

import com.example.filmsappcompose.domain.use_case.*
import com.example.filmsappcompose.presentation.main_screen.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainScreenViewModelFactory(
        getPopularMoviesNetworkUseCase: GetPopularMoviesNetworkUseCase,
        searchMoviesUseCase: SearchMoviesUseCase,
        getPopularMoviesLocalUseCase: GetPopularMoviesLocalUseCase,
        insertMoviesUseCase: InsertMoviesUseCase,
        getGenreUseCase: GetGenreUseCase,
        filterByGenresUseCase:FilterByGenresUseCase,
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getPopularMoviesNetworkUseCase,
            searchMoviesUseCase,
            getPopularMoviesLocalUseCase,
            insertMoviesUseCase,
            getGenreUseCase,
            filterByGenresUseCase
        )
    }
}