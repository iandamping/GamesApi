package com.junemon.gamesapi.data.datasource.remote

import com.ian.app.helper.base.BaseDataSource
import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.data.data.datasource.GamesRemoteDataSource
import com.junemon.gamesapi.data.datasource.model.mapToDomain
import com.junemon.gamesapi.domain2.model.GameModel

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GamesRemoteDataSourceImpl(private val api: GamesApi) : BaseDataSource(), GamesRemoteDataSource {

    override suspend fun get(): ResultToConsume<List<GameModel>> {
        val firstData = getResult { api.getGames() }
        val firstDataMap = firstData.data?.data?.mapToDomain()
        return ResultToConsume(firstData.status, firstDataMap!!, firstData.message)
    }
}