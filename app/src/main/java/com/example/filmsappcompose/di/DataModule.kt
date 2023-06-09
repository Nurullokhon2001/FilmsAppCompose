package com.example.filmsappcompose.di

import android.content.Context
import com.example.filmsappcompose.data.local.MoviesDb
import com.example.filmsappcompose.data.local.dao.MoviesDao
import com.example.filmsappcompose.data.local.dao.RemoteKeysDao
import com.example.filmsappcompose.data.local.db.MoviesLocalDataSource
import com.example.filmsappcompose.data.remote.network.ApiInterface
import com.example.filmsappcompose.data.remote.network.MoviesRemoteDataSource
import com.example.filmsappcompose.data.remote.network.RetrofitClient
import com.example.filmsappcompose.data.repository.RepositoryImpl
import com.example.filmsappcompose.domain.Repository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideDb(context: Context): MoviesDb {
        return MoviesDb.getDatabase(context)
    }

    @Provides
    fun provideRemoteKeysDao(moviesDb: MoviesDb):RemoteKeysDao = moviesDb.remoteKeysDao()

    @Provides
    fun provideRemoteMoviesDao(moviesDb: MoviesDb):MoviesDao = moviesDb.moviesDao()

    @Provides
    fun provideApi(): ApiInterface {
        return RetrofitClient.create()
    }

    @Provides
    fun provideMoviesLocalDataSource(
        moviesDao: MoviesDao,
        remoteKeysDao: RemoteKeysDao,
        apiInterface: ApiInterface
    ): MoviesLocalDataSource {
        return MoviesLocalDataSource(moviesDao,remoteKeysDao, apiInterface)
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