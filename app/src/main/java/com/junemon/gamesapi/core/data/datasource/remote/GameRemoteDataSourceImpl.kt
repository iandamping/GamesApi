package com.junemon.gamesapi.core.data.datasource.remote

import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.di.module.IoDispatcher
import com.junemon.gamesapi.core.model.DataHelper
import com.junemon.gamesapi.core.model.Results
import com.junemon.gamesapi.core.network.ApiInterface
import com.junemon.gamesapi.core.network.BaseSources
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameRemoteDataSourceImpl @Inject constructor(
    private val api: ApiInterface,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseSources(), GameRemoteDataSource {

    override suspend fun getListGames() = flow {
        when (val responses = get { api.getListGames() }) {
            is Results.Success -> {
                emit(DataHelper.RemoteSourceValue(responses.data.data))
            }
            is Results.Error -> {
                emit(DataHelper.RemoteSourceError(responses.exception))
            }

        }
    }.flowOn(ioDispatcher)
}