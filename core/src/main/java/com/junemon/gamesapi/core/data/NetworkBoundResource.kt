package com.junemon.gamesapi.core.data

import com.junemon.gamesapi.core.domain.model.DataHelper
import com.junemon.gamesapi.core.domain.model.Resources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resources<ResultType>> = flow {
        emit(Resources.Loading())

        val dbSource = loadFromDB().first()

        if (shouldFetch(dbSource)) {
            emit(Resources.Loading())
            when (val apiResponse = createCall().first()) {

                is DataHelper.RemoteSourceValue -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Resources.Success(
                            it
                        )
                    })
                }
                is DataHelper.RemoteSourceEmpty -> {
                    emitAll(loadFromDB().map {
                        Resources.Success(
                            it
                        )
                    })
                }
                is DataHelper.RemoteSourceError -> {
                    onFetchFailed()
                    emit(
                        Resources.Error(
                            apiResponse.exception
                        )
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resources.Success(
                    it
                )
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<DataHelper<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resources<ResultType>> = result
}