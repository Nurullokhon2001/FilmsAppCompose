package com.example.filmsappcompose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmsappcompose.domain.model.Movie

private const val BASE_URL_IMAGE = "http://image.tmdb.org/t/p/w500/"

@Entity(tableName = "moviesEntity")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "rating") val rating: Float = 0f,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "age") val age: String = "0",
)

fun MovieEntity.toDomain() = Movie(
    id = id,
    name = name,
    image = BASE_URL_IMAGE + image,
    rating = rating.div(2),
    description = description,
    age = age,
    genres = emptyList()
)

fun Movie.toData() = MovieEntity(
    id = id,
    name = name,
    image = BASE_URL_IMAGE + image,
    rating = rating.div(2),
    description = description,
    age = age
)

