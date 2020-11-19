package com.junemon.gamesapi.core.data.datasource.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity


/**
 * Created by Ian Damping on 20,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}