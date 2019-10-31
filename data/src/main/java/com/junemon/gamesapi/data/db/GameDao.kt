package com.junemon.gamesapi.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.junemon.gamesapi.data.datasource.model.GameDbEntity

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Dao
interface GameDao {
    @Query("SELECT * FROM game_table")
    fun loadGame(): LiveData<List<GameDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(vararg gameData: GameDbEntity)

    @Query("DELETE FROM game_table")
    suspend fun deleteAllGame()
}