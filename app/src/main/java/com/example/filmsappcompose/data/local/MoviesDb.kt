package com.example.filmsappcompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filmsappcompose.data.local.dao.MoviesDao
import com.example.filmsappcompose.data.local.dao.RemoteKeysDao
import com.example.filmsappcompose.data.local.entity.MovieEntity
import com.example.filmsappcompose.data.local.entity.RemoteKeysEntity

@Database(entities = [MovieEntity::class,RemoteKeysEntity::class], version = 1, exportSchema = false)
abstract class MoviesDb : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDb? = null

        fun getDatabase(context: Context): MoviesDb {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        MoviesDb::class.java,
                        "moviesDb"
                    )
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
