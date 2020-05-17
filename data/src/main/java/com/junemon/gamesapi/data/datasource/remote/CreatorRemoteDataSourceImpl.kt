package com.junemon.gamesapi.data.datasource.remote

import com.junemon.gamesapi.data.data.datasource.CreatorRemoteDataSource
import com.junemon.gamesapi.data.datasource.model.mapRemoteItemToDomain
import com.junemon.gamesapi.domain2.model.CreatorData
import io.reactivex.Single

/**
 * Created by Ian Damping on 10,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class CreatorRemoteDataSourceImpl(private val api:GamesApi):CreatorRemoteDataSource {

    override fun get(): Single<List<CreatorData>> {
        return api.getCreator().map { it.data.mapRemoteItemToDomain() }
    }
}