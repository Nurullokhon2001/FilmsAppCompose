package com.example.filmsappcompose.data.remote

import com.example.filmsappcompose.data.dto.ActorsDto
import com.example.filmsappcompose.data.dto.MovieDetailsDto
import com.example.filmsappcompose.data.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "8cc6dea6e8f86bf650389d5009fc4980"

interface ApiInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
    ): MoviesDto

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String,
    ): MoviesDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): MovieDetailsDto

    @GET("movie/{movie_id}/credits?")
    suspend fun getMovieActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = API_KEY,
    ): ActorsDto
}