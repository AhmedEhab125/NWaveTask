package com.example.nwavetask.network

sealed class ApiState {
    class Success<T>(val data: T) : ApiState()
    class Failure(val error: Throwable) : ApiState()
    object Loading : ApiState()
}