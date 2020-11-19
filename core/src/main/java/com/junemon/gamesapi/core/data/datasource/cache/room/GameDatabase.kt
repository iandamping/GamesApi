package com.junemon.gamesapi.core.data.datasource.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameFavoriteEntity

/**
 * Created by Ian Damping on 20,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Database(entities = [GameEntity::class,GameFavoriteEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun gameFavoriteDao(): GameFavoriteDao
}