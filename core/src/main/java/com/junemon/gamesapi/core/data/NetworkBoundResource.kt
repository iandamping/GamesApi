package com.junemon.gamesapi.core.data

import com.junemon.gamesapi.core.domain.model.ConsumeResult
import com.junemon.gamesapi.core.domain.model.DataHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<ConsumeResult<ResultType>> = flow {
        emit(ConsumeResult.Loading)
        val dbSource = loadFromDB().first()

        if (shouldFetch(dbSource)) {
            emit(ConsumeResult.Loading)

            when (val apiResponse = createCall().first()) {

                is DataHelper.RemoteSourceValue -> {
                    saveCallResult(apiResponse.data)
                    emit(ConsumeResult.Complete)
                    emitAll(loadFromDB().map {
                        ConsumeResult.ConsumeData(
                            it
                        )
                    })
                }
                is DataHelper.RemoteSourceEmpty -> {
                    emit(ConsumeResult.Complete)
                    emitAll(loadFromDB().map {
                        ConsumeResult.ConsumeData(
                            it
                        )
                    })
                }
                is DataHelper.RemoteSourceError -> {
                    emit(ConsumeResult.Complete)
                    onFetchFailed()
                    emit(
                        ConsumeResult.ErrorHappen(
                            apiResponse.exception
                        )
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                emit(ConsumeResult.Complete)
                ConsumeResult.ConsumeData(
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

    fun asFlow(): Flow<ConsumeResult<ResultType>> = result
}