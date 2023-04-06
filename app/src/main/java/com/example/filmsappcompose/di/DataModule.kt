package com.example.filmsappcompose.di

import android.content.Context
import com.example.filmsappcompose.data.local.MoviesDb
import com.example.filmsappcompose.data.local.dao.MoviesDao
import com.example.filmsappcompose.data.local.db.MoviesLocalDataSource
import com.example.filmsappcompose.data.remote.network.ApiInterface
import com.example.filmsappcompose.data.remote.network.MoviesRemoteDataSource
import com.example.filmsappcompose.data.remote.network.RetrofitClient
import com.example.filmsappcompose.data.repository.RepositoryImpl
import com.example.filmsappcompose.domain.Repository
import dagger.Module
import dagger.Provides

@Module
class DataModule() {

    @Provides
    fun provideDb(context: Context): MoviesDao {
        return MoviesDb.getDatabase(context).moviesDao()
    }

    @Provides
    fun provideApi(): ApiInterface {
        return RetrofitClient.create()
    }

    @Provides
    fun provideMoviesLocalDataSource(moviesDao: MoviesDao): MoviesLocalDataSource {
        return MoviesLocalDataSource(moviesDao)
    }

    @Provides
    fun provideMoviesRemoteDataSource(apiInterface: ApiInterface): MoviesRemoteDataSource {
        return MoviesRemoteDataSource(apiInterface)
    }

    @Provides
    fun provideRepository(
        moviesLocalDataSource: MoviesLocalDataSource,
        moviesRemoteDataSource: MoviesRemoteDataSource
    ): Repository {
        return RepositoryImpl(moviesRemoteDataSource, moviesLocalDataSource)
    }

}