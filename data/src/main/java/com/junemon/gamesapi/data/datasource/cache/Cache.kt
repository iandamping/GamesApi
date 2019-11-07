package com.junemon.gamesapi.data.datasource.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.data.datasource.remote.ApiConstant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

/**
 * Created by Ian Damping on 05,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
@ExperimentalCoroutinesApi
fun <T, A> ssotResultFlow(
    databaseQuery: () -> Flow<T>,
    networkCall: suspend () -> ResultToConsume<A>,
    saveCallResult: suspend (A) -> Unit
): Flow<ResultToConsume<T>> = flow {
        // emit loading
        emit(ResultToConsume.loading())

        // map database source into an object
        val source = databaseQuery.invoke().map {
            ResultToConsume.success(it)
        }

        // variable that invoked networkCall function
        val responseStatus = networkCall.invoke()
        when {
            responseStatus.data != null -> {
                if (responseStatus.status == ResultToConsume.Status.SUCCESS) {
                    saveCallResult(responseStatus.data!!)
                } else if (responseStatus.status == ResultToConsume.Status.ERROR) {
                    emit(ResultToConsume.error(responseStatus.message!!))
                }
            }
            else -> {
                if (responseStatus.message != null) {
                    emit(ResultToConsume.error(responseStatus.message!!))
                } else {
                    emit(ResultToConsume.error("Unable to resolve host ${ApiConstant.baseUrl}"))
                }
                // emit database object into flow
                emitAll(source)
            }
        }
        // emit database object into flow
        emitAll(source)
    }

@ExperimentalCoroutinesApi
fun <T, A> ssotResultFlowLiveDataResult(
    databaseQuery: () -> Flow<T>,
    networkCall: suspend () -> ResultToConsume<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<ResultToConsume<T>> = flow {
        // emit loading
        emit(ResultToConsume.loading())

        // map database source into an object
        val source = databaseQuery.invoke().map {
            ResultToConsume.success(it)
        }


        // variable that invoked networkCall function
        val responseStatus = networkCall.invoke()
        when {
            responseStatus.data != null -> {
                if (responseStatus.status == ResultToConsume.Status.SUCCESS) {
                    saveCallResult(responseStatus.data!!)
                } else if (responseStatus.status == ResultToConsume.Status.ERROR) {
                    emit(ResultToConsume.error(responseStatus.message!!))
                }
            }
            else -> {
                if (responseStatus.message != null) {
                    emit(ResultToConsume.error(responseStatus.message!!))
                } else {
                    emit(ResultToConsume.error("Unable to resolve host ${ApiConstant.baseUrl}"))
                }
                // emit database object into flow
                source.collect {
                    emit(it)
                }
            }
        }
        // emit database object into flow
        source.collect {
            emit(it)
        }
    }.asLiveData()
    //transform it into livedata

