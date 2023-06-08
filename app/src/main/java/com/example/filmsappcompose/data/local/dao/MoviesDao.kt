package com.example.filmsappcompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmsappcompose.data.local.entity.MovieEntity

@androidx.room.Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("Select * from moviesEntity")
    suspend fun getMovies(): List<MovieEntity>
    @Query("Select * from moviesEntity")
     fun getMoviesPaging(): PagingSource<Int, MovieEntity>

    @Query("Delete from moviesEntity")
    suspend fun deleteMovies()

}