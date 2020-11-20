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

        val dbSource = loadFromDB().first()

        if (shouldFetch(dbSource)) {
            when (val apiResponse = createCall().first()) {

                is DataHelper.RemoteSourceValue -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        ConsumeResult.ConsumeData(
                            it
                        )
                    })
                }
                is DataHelper.RemoteSourceEmpty -> {
                    emitAll(loadFromDB().map {
                        ConsumeResult.ConsumeData(
                            it
                        )
                    })
                }
                is DataHelper.RemoteSourceError -> {
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