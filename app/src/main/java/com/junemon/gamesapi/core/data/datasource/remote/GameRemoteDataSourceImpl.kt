package com.junemon.gamesapi.core.data.datasource.remote

import com.junemon.gamesapi.core.data.data.datasource.GameRemoteDataSource
import com.junemon.gamesapi.core.di.module.DefaultDispatcher
import com.junemon.model.DataHelper
import com.junemon.model.games.GamesModel
import com.junemon.model.Results
import com.junemon.gamesapi.core.network.ApiInterface
import com.junemon.gamesapi.core.network.BaseSources
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameRemoteDataSourceImpl @Inject constructor(
    private val api: ApiInterface,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseSources(), GameRemoteDataSource {

    override suspend fun getListGames(): DataHelper<List<GamesModel>> {
        val results:CompletableDeferred<DataHelper<List<GamesModel>>> = CompletableDeferred()
        withContext(defaultDispatcher){
            when (val responses = oneShotCalls { api.getListGames() }) {
                is Results.Success -> {
                    results.complete(DataHelper.RemoteSourceValue( responses.data.data))
                }
                is Results.Error -> {
                    results.complete(DataHelper.RemoteSourceError(responses.exception))
                }
            }
        }
        return results.await()
    }
}