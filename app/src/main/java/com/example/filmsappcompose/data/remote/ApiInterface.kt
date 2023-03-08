package com.example.filmsappcompose.data.remote

import com.example.filmsappcompose.data.dto.Movies
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "8cc6dea6e8f86bf650389d5009fc4980"

interface ApiInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        // @Query("language") language: String = "ru-RU"
    ): Movies
}