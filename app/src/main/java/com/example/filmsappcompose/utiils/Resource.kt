package com.example.filmsappcompose.utiils

import timber.log.Timber
import java.util.concurrent.CancellationException


sealed class Resource<out S, out E> {
    data class Success<out S>(val data: S) : Resource<S, Nothing>()
    data class Error<out E>(val error: E) : Resource<Nothing, E>()
}

inline fun <S, E> Resource<S, E>.doOnSuccess(block: (S) -> Unit): Resource<S, E> {
    if (this is Resource.Success) {
        block(this.data)
    }
    return this
}

inline fun <S, E> Resource<S, E>.doOnError(block: (E) -> Unit): Resource<S, E> {
    if (this is Resource.Error) {
        Timber.e("Result.doOnError: ".plus(this.error.toString()))
        block(this.error)
    }
    return this
}

inline fun <S, R> S.runOperationCatching(block: S.() -> R): Resource<R, Throwable> {
    return try {
        Resource.Success(block())
    } catch (e: CancellationException) {
        Timber.e("runOperationCatching: ".plus(e.toString()))
        throw e
    } catch (e: Throwable) {
        Timber.e("runOperationCatching: ".plus(e.toString()))
        Resource.Error(e)
    }
}