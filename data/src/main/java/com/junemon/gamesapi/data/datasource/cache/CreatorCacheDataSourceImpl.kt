package com.junemon.gamesapi.data.datasource.cache

import com.junemon.gamesapi.data.data.datasource.CreatorCacheDataSource
import com.junemon.gamesapi.data.datasource.model.CreatorDbEntity
import com.junemon.gamesapi.data.datasource.model.mapToDomain
import com.junemon.gamesapi.data.db.GameDatabase
import com.junemon.gamesapi.domain2.model.CreatorData
import io.reactivex.Single

/**
 * Created by Ian Damping on 10,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class CreatorCacheDataSourceImpl(private val db:GameDatabase):CreatorCacheDataSource {

    override fun set(data: List<CreatorDbEntity>){
        db.creatorDao().insertCreator(*data.toTypedArray())
    }

    override fun get(): Single<List<CreatorData>> {
        return db.creatorDao().loadCreator().map { it.mapToDomain() }
    }
}