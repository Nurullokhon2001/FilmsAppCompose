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
    fun provideGetMoviesUseCase(repository: Repository): GetPopularMoviesLocalUseCase {
        return GetPopularMoviesLocalUseCase(repository)
    }

    @Provides
    fun provideGetPopularMoviesUseCase(repository: Repository): GetPopularMoviesNetworkUseCase {
        return GetPopularMoviesNetworkUseCase(repository)
    }

    @Provides
    fun provideInsertMoviesUseCase(repository: Repository): InsertMoviesUseCase {
        return InsertMoviesUseCase(repository)
    }

    @Provides
    fun provideSearchMoviesUseCase(repository: Repository): SearchMoviesUseCase {
        return SearchMoviesUseCase(repository)
    }

    @Provides
    fun provideGetGenreUseCase(repository: Repository): GetGenreUseCase {
        return GetGenreUseCase(repository)
    }

    @Provides
    fun provideFilterByGenresUseCase(): FilterByGenresUseCase {
        return FilterByGenresUseCase()
    }
}