package com.junemon.gamesapi.data.data.datasource

import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.data.datasource.model.CreatorDbEntity
import com.junemon.gamesapi.domain2.model.CreatorData
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 10,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface CreatorRemoteDataSource {
    fun get(): Single<List<CreatorData>>
}

interface CreatorCacheDataSource {

    fun set(data: List<CreatorDbEntity>)

    fun get(): Single<List<CreatorData>>
}