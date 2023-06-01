package com.example.coroutine.utils

sealed class Resource<out T>(val status: Status, open val data: T?, open val message: String?) {

    data class Success<T>(override val data: T?) :
        Resource<T>(status = Status.SUCCESS, data = data, message = null)

    data class Error<T>(override val data: T?, override val message: String) :
        Resource<T>(status = Status.ERROR, data = data, message = message)

    data class Loading<T>(override val data: T?) : Resource<T>(Status.LOADING, data, null)
}
