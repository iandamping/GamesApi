package com.junemon.model

/**
 * Created by Ian Damping on 22,January,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
sealed class RemoteDataSourceHelper<out R> {
    data class Success<out T>(val data: T) : RemoteDataSourceHelper<T>()
    data class Error(val exception: Exception) : RemoteDataSourceHelper<Nothing>()
}