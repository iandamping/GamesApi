package com.junemon.gamesapi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun <T> singleResultLiveData(networkCall: suspend () -> ResultToConsume<T>): LiveData<ResultToConsume<T>> =
    liveData(Dispatchers.IO) {
        emit(ResultToConsume.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == ResultToConsume.Status.SUCCESS) {
            emit(ResultToConsume.success(responseStatus.data!!))
        } else if (responseStatus.status == ResultToConsume.Status.ERROR) {
            emit(ResultToConsume.error(networkCall.invoke().message!!))
        }
    }
