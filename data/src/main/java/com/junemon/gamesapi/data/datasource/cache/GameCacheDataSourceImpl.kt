package com.junemon.gamesapi.data.datasource.cache

import androidx.lifecycle.LiveData
import com.junemon.gamesapi.data.data.datasource.GameCacheDataSource
import com.junemon.gamesapi.data.datasource.model.mapToDatabase
import com.junemon.gamesapi.data.datasource.model.mapToDomain
import com.junemon.gamesapi.data.db.GameDatabase
import com.junemon.gamesapi.domain2.model.GameModel

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameCacheDataSourceImpl(private val db: GameDatabase) : GameCacheDataSource {

    override suspend fun set(data: List<GameModel>) {
        db.gameDao().insertGame(*data.mapToDatabase().toTypedArray())
    }

    override fun get(): LiveData<List<GameModel>> {
        return db.gameDao().loadGame().mapToDomain()
    }
}