package com.example.filmsappcompose.di

import com.example.filmsappcompose.domain.Repository
import com.example.filmsappcompose.domain.use_case.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetMovieActorsUseCase(repository: Repository): GetMovieActorsUseCase {
        return GetMovieActorsUseCase(repository)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(repository: Repository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }

    @Provides
    fun provideGetGenreUseCase(repository: Repository): GetGenreUseCase {
        return GetGenreUseCase(repository)
    }

}