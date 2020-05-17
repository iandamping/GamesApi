package com.junemon.gamesapi.data.data.repository

import com.junemon.gamesapi.data.data.datasource.CreatorCacheDataSource
import com.junemon.gamesapi.data.data.datasource.CreatorRemoteDataSource
import com.junemon.gamesapi.data.datasource.model.mapToDatabase
import com.junemon.gamesapi.domain2.model.CreatorData
import com.junemon.gamesapi.domain2.repository.CreatorRepository
import io.reactivex.Single

/**
 * Created by Ian Damping on 10,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class CreatorRepositoryImpl(
    private val remoteDataSource: CreatorRemoteDataSource,
    private val cacheDataSource: CreatorCacheDataSource
) : CreatorRepository {

    override fun get(): Single<List<CreatorData>> {
        return remoteDataSource.get()
            .map { cacheDataSource.set(it.mapToDatabase()) }
            .flatMap { cacheDataSource.get() }
    }
}