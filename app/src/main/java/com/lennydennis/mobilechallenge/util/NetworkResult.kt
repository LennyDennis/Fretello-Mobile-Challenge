package com.lennydennis.mobilechallenge.util

sealed class NetworkResult<out T> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}
