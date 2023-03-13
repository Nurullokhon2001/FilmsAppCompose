package com.example.filmsappcompose.data.remote

import com.example.filmsappcompose.data.dto.MoviesDto
import okhttp3.Response
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

    @GET("/movie/{movie_id}")
    fun getMovieDetails(
        @Query("api_key") apiKey: String = API_KEY,
        @Path("movie_id") movieId: Int
    ): Response
}