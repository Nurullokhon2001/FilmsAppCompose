package com.example.filmsappcompose.main_screen

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.filmsappcompose.main_screen.domain.Film
import com.google.gson.Gson

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val _films = mutableStateOf(
        Gson().fromJson(
            loadJsonFromAssets(application),
            Array<Film>::class.java
        ).toList()
    )
    val films: State<List<Film>> = _films

    private fun loadJsonFromAssets(context: Context): String {
        var result = ""
        runCatching {
            val input = context.assets.open("films.json")
            val size = input.available()
            val bytes = ByteArray(size)
            input.read(bytes)
            input.close()
            result = String(bytes)
        }.onFailure {
            Log.e("loadJsonFromAssets", "loadJsonFromAssets: ${it.message} ")
        }
        return result
    }
}