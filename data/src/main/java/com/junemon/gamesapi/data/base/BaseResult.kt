package com.junemon.gamesapi.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.data.datasource.remote.ApiConstant
import com.junemon.model.DataHelper
import com.junemon.model.RepositoryDataHelper
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Created by Ian Damping on 30,November,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
internal class BaseResult : ExtractResult {

    private val errorHeppen: CompletableDeferred<Throwable> = CompletableDeferred()

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        errorHeppen.complete(e)
    }

    override fun <T, A> ssotLiveDataResult(
        databaseQuery: () -> Flow<T>,
        networkCall: suspend () -> DataHelper<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<RepositoryDataHelper<T>> {
        return liveData(Dispatchers.IO + getJobErrorHandler()) {
            //emit loading
            val disposables = emitSource(databaseQuery.invoke().map {
                RepositoryDataHelper.Loading
            }.asLiveData())
            val responseStatus = networkCall.invoke()
            disposables.dispose()

            when (responseStatus) {
                is DataHelper.RemoteSourceError -> {
                    emitSource(databaseQuery.invoke().map {
                        RepositoryDataHelper.Error(
                            responseStatus.exception,
                            it
                        )
                    }.asLiveData())
                }
                is DataHelper.RemoteSourceValue -> {
                    saveCallResult(responseStatus.data!!)
                    emitSource(databaseQuery.invoke().map { RepositoryDataHelper.Success(it) }.asLiveData())
                }
            }

            /*val errors = errorHeppen.await()
            if (errors!=null){
                emitSource(databaseQuery.invoke().map {
                    HelperResults.Error(Exception(errors), it)
                }.asLiveData())
            } else{
                saveCallResult(responseStatus)
                emitSource(databaseQuery.invoke().map { HelperResults.Success(it) }.asLiveData())
            }*/

            /*  CoroutineExceptionHandler { coroutineContext, throwable ->
                  emitSource(databaseQuery.invoke().map {
                      HelperResults.Error(throwable, it)
                  }.asLiveData())
              }*/

            /*when (responseStatus) {
                is HelperResults.Error -> {
                    emitSource(databaseQuery.invoke().map {
                        HelperResults.Error(
                            responseStatus.exception,
                            it
                        )
                    }.asLiveData())
                }
                is HelperResults.Success -> {
                    saveCallResult(responseStatus.data)
                    emitSource(databaseQuery.invoke().map { HelperResults.Success(it) }.asLiveData())
                }
            }*/

        }
    }

    @ExperimentalCoroutinesApi
    override fun <T, A> ssotFlowResult(
        databaseQuery: () -> Flow<T>,
        networkCall: suspend () -> ResultToConsume<A>,
        saveCallResult: suspend (A) -> Unit
    ): Flow<ResultToConsume<T>> {
        return flow {
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
    }

    override fun <T> remoteLiveDataResult(networkCall: suspend () -> ResultToConsume<T>): LiveData<ResultToConsume<T>> {
        return liveData {
            emit(ResultToConsume.loading())
            val responseStatus = networkCall.invoke()
            when {
                responseStatus.data != null -> {
                    if (responseStatus.status == ResultToConsume.Status.SUCCESS) {
                        emit(ResultToConsume.success(responseStatus.data!!))
                    } else if (responseStatus.status == ResultToConsume.Status.ERROR) {
                        emit(ResultToConsume.error(networkCall.invoke().message!!))
                    }
                }
                else -> {
                    if (responseStatus.message != null) {
                        emit(ResultToConsume.error(responseStatus.message!!))
                    } else emit(ResultToConsume.error("Unable to resolve host ${ApiConstant.baseUrl}"))
                }
            }
        }
    }

    override fun <T> remoteFlowResult(networkCall: suspend () -> ResultToConsume<T>): Flow<ResultToConsume<T>> {
        return flow {
            emit(ResultToConsume.loading())
            val responseStatus = networkCall.invoke()
            when {
                responseStatus.data != null -> {
                    if (responseStatus.status == ResultToConsume.Status.SUCCESS) {
                        emit(ResultToConsume.success(responseStatus.data!!))
                    } else if (responseStatus.status == ResultToConsume.Status.ERROR) {
                        emit(ResultToConsume.error(networkCall.invoke().message!!))
                    }
                }
                else -> {
                    if (responseStatus.message != null) {
                        emit(ResultToConsume.error(responseStatus.message!!))
                    } else emit(ResultToConsume.error("Unable to resolve host ${ApiConstant.baseUrl}"))
                }
            }
        }
    }
}

interface ExtractResult {

    fun <T, A> ssotLiveDataResult(
        databaseQuery: () -> Flow<T>, networkCall: suspend () -> DataHelper<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<RepositoryDataHelper<T>>

    fun <T, A> ssotFlowResult(
        databaseQuery: () -> Flow<T>, networkCall: suspend () -> ResultToConsume<A>,
        saveCallResult: suspend (A) -> Unit
    ): Flow<ResultToConsume<T>>

    fun <T> remoteLiveDataResult(networkCall: suspend () -> ResultToConsume<T>): LiveData<ResultToConsume<T>>

    fun <T> remoteFlowResult(networkCall: suspend () -> ResultToConsume<T>): Flow<ResultToConsume<T>>
}