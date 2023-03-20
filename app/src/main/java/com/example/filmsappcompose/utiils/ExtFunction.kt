package com.example.filmsappcompose.utiils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmsappcompose.data.dto.MovieDto
import com.google.gson.Gson
import timber.log.Timber
import java.util.*

fun Context.loadJsonFromAssets(): String {
    var result = ""
    runCatching {
        val input = this.assets.open("films.json")
        val size = input.available()
        val bytes = ByteArray(size)
        input.read(bytes)
        input.close()
        result = String(bytes)
    }.onFailure {
        Timber.tag("loadJsonFromAssets").e("loadJsonFromAssets: %s", it.message)
    }
    return result
}

fun Context.getMokData(): List<MovieDto> {
    return Gson().fromJson(
        this.loadJsonFromAssets(),
        Array<MovieDto>::class.java
    ).toList()
}

inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
    }