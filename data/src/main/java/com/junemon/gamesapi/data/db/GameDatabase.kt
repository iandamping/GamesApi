package com.junemon.gamesapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junemon.gamesapi.data.datasource.model.GameDbEntity

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Database(entities = [GameDbEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}
