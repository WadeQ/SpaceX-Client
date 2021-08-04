package com.wadektech.spacexclient.utils

import okhttp3.ResponseBody


sealed class State<out T>{

    data class Success<T>(var value:T): State<T>()

    data class Failure(
        val isNetworkError : Boolean?,
        val errorBody: ResponseBody?
    ) : State<Nothing>()

    object Loading : State<Nothing>()
}
