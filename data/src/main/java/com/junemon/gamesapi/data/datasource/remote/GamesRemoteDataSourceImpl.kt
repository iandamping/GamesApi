package com.junemon.gamesapi.data.datasource.remote

import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.data.base.BaseDataSources
import com.junemon.gamesapi.data.data.datasource.GamesRemoteDataSource
import com.junemon.gamesapi.data.datasource.model.mapToDomain
import com.junemon.gamesapi.domain2.model.GameModel
import kotlinx.coroutines.CompletableDeferred

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GamesRemoteDataSourceImpl(private val api: GamesApi) : BaseDataSources(),
    GamesRemoteDataSource {

    override suspend fun get(): ResultToConsume<List<GameModel>> {
        val results = CompletableDeferred<ResultToConsume<List<GameModel>>>()
        try {
            val firstData = api.getGames().getResult()
            val firstDataMap = firstData.data?.data?.mapToDomain()
            checkNotNull(firstDataMap){
                " data from service is null"
            }
            assert(firstDataMap.isNotEmpty())
            results.complete(ResultToConsume(firstData.status, firstDataMap, firstData.message))
        } catch (e: Exception) {
            results.complete(ResultToConsume(ResultToConsume.Status.ERROR, null, e.message))
        }
        return results.await()
        // val firstData = api.getGamesCancelation().doOneShot()
        /* val firstData = getResult { api.getGames() }
         val firstDataMap = firstData.data?.data?.mapToDomain()
         return ResultToConsume(firstData.status, firstDataMap, firstData.message)*/
    }
}