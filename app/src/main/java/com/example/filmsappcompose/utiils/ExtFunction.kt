package com.example.filmsappcompose.utiils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.filmsappcompose.main_screen.domain.Film
import com.google.gson.Gson
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
        Log.e("loadJsonFromAssets", "loadJsonFromAssets: ${it.message} ")
    }
    return result
}

fun Context.toList(): List<Film> {
    return Gson().fromJson(
        this.loadJsonFromAssets(),
        Array<Film>::class.java
    ).toList()
}