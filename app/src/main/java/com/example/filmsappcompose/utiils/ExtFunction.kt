package com.example.filmsappcompose.utiils

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmsappcompose.data.dto.MovieDto
import com.google.gson.Gson
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Long.convertLongToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy.MM.dd")
    return format.format(date)
}

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