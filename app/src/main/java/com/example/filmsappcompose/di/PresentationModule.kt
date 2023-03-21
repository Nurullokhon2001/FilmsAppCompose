package com.example.filmsappcompose.di

import com.example.filmsappcompose.domain.use_case.GetMoviesUseCase
import com.example.filmsappcompose.domain.use_case.GetPopularMoviesUseCase
import com.example.filmsappcompose.domain.use_case.InsertMoviesUseCase
import com.example.filmsappcompose.domain.use_case.SearchMoviesUseCase
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
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getPopularMoviesUseCase,
            searchMoviesUseCase,
            getMoviesUseCase,
            insertMoviesUseCase,
        )
    }
}