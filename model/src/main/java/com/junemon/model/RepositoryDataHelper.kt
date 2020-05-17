package com.junemon.model

sealed class RepositoryDataHelper<out R> {
    data class Success<out T>(val data: T) : RepositoryDataHelper<T>()
    object Loading : RepositoryDataHelper<Nothing>()
    data class Error<out T>(val exception: Exception,val cache:T?) : RepositoryDataHelper<T>()
}