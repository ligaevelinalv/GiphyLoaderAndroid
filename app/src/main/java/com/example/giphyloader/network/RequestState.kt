package com.example.giphyloader.network

sealed class RequestState<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : RequestState<T>(data = data)
    class Error<T>(errorMessage: String) : RequestState<T>(errorMessage = errorMessage)
    class Loading<T>: RequestState<T>()
    class Idle<T>: RequestState<T>()
}
