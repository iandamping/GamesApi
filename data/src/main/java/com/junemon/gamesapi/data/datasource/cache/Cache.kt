package com.junemon.gamesapi.data.datasource.cache

import androidx.lifecycle.LiveData
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
): Flow<ResultToConsume<T>> =
    flow {
        // emit loading
        emit(ResultToConsume.loading())

        // emit livedata from database
        val source = databaseQuery.invoke().map {
            // emit succeed from database
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
                // emit from database
                emitAll(source)
            }
        }
        // emit from database
        emitAll(source)
    }

@ExperimentalCoroutinesApi
fun <T, A> ssotResultFlowLiveDataResult(
    databaseQuery: () -> Flow<T>,
    networkCall: suspend () -> ResultToConsume<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<ResultToConsume<T>> =
    liveData {
        // emit loading
        emit(ResultToConsume.loading())

        // emit livedata from database
        val source = databaseQuery.invoke().map {
            // emit succeed from database
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
                // emit from database
                source.collect {
                    emit(it)
                }
            }
        }
        // emit from database
        source.collect {
            emit(it)
        }
    }

