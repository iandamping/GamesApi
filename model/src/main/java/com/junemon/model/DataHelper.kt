package com.junemon.model

/**
 * Created by Ian Damping on 20,January,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
sealed class DataHelper<out T>{
    data class RemoteSourceValue<out T>(val data: T) : DataHelper<T>()
    data class RemoteSourceError(val exception: Exception) : DataHelper<Nothing>()
}
