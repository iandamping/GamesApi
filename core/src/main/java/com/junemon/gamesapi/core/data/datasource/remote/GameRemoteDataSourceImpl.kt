package com.junemon.gamesapi.core.data.datasource.remote

import com.junemon.gamesapi.core.data.datasource.remote.network.ApiInterface
import com.junemon.gamesapi.core.data.datasource.remote.network.BaseSources
import com.junemon.gamesapi.core.domain.model.DataHelper
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameDetailResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameSearchResponse
import com.junemon.gamesapi.core.domain.model.Results
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameRemoteDataSourceImpl(
    private val api: ApiInterface,
    private val defaultDispatcher: CoroutineDispatcher
) : BaseSources(), GameRemoteDataSource {

    override fun getFlowListGames(): Flow<DataHelper<List<GameResponse>>> {
        return flow {
            when (val responses = oneShotCalls { api.getListGames() }) {
                is Results.Success -> {
                    if (responses.data.data.isNotEmpty()){
                        emit(DataHelper.RemoteSourceValue(responses.data.data))
                    }else emit(DataHelper.RemoteSourceEmpty)
                }
                is Results.Error -> {
                    emit(DataHelper.RemoteSourceError(responses.exception))
                }
            }
        }.flowOn(defaultDispatcher).conflate()
    }

    override suspend fun getDetailGames(gameId: Int): DataHelper<GameDetailResponse> =
        withContext(defaultDispatcher) {
            when (val responses = oneShotCalls { api.getDetailGames(gameId) }) {
                is Results.Success -> {
                    DataHelper.RemoteSourceValue(responses.data)
                }
                is Results.Error -> {
                    DataHelper.RemoteSourceError(responses.exception)
                }
            }
        }

    override suspend fun getSearchGames(query: String): DataHelper<List<GameSearchResponse>> =
        withContext(defaultDispatcher) {
            when (val responses = oneShotCalls { api.getSearchGames(query) }) {
                is Results.Success -> {
                    if (responses.data.data.isNotEmpty()){
                        DataHelper.RemoteSourceValue(responses.data.data)
                    }else DataHelper.RemoteSourceEmpty
                }
                is Results.Error -> {
                    DataHelper.RemoteSourceError(responses.exception)
                }
            }
        }
}