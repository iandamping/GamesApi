package com.junemon.gamesapi.core.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junemon.gamesapi.core.cache.dao.GameDao
import com.junemon.gamesapi.core.cache.model.GameEntity


/**
 * Created by Ian Damping on 20,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}