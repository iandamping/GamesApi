package com.junemon.gamesapi.data.datasource.remote

import com.junemon.gamesapi.data.base.BaseDataSources
import com.junemon.gamesapi.data.data.datasource.GamesRemoteDataSource
import com.junemon.gamesapi.data.datasource.model.mapToDomain
import com.junemon.gamesapi.domain2.model.GameData
import com.junemon.model.DataHelper
import com.junemon.model.RemoteDataSourceHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GamesRemoteDataSourceImpl(private val api: GamesApi) : BaseDataSources(),
    GamesRemoteDataSource {

    @ExperimentalCoroutinesApi
    override suspend fun get(): DataHelper<List<GameData>> {
        val firstData = api.getGames().doOneShots()
        return suspendCancellableCoroutine { cc ->
                when(firstData){
                    is RemoteDataSourceHelper.Error ->{
                        cc.resume(DataHelper.RemoteSourceError(firstData.exception))
                    }
                    is RemoteDataSourceHelper.Success ->{
                        val firstDataMap = firstData.data.data.mapToDomain()
                        cc.resume(DataHelper.RemoteSourceValue(firstDataMap))
                    }
                }

        }
        /*val results = CompletableDeferred<List<GameData>>()
        try {
            val firstData = api.getGames().getResult()
            val firstDataMap = firstData.data?.data?.mapToDomain()
            checkNotNull(firstDataMap){
                " ${firstData.message} "
            }
            assert(firstDataMap.isNotEmpty())
            results.complete(firstDataMap)
        } catch (e: Exception) {
            results.complete(HelperResults.Error(e))
        }
        return results.await()*/
    }
}