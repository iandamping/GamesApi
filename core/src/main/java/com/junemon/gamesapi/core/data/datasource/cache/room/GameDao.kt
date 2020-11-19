package com.junemon.gamesapi.core.data.datasource.cache.room

import androidx.room.*
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import kotlinx.coroutines.flow.Flow


/**
 * Created by Ian Damping on 20,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun loadGame(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(vararg tagsData: GameEntity)

    @Query("DELETE FROM game")
    suspend fun deleteAllGame()

    @Transaction
    suspend fun insertAllGame(vararg tagsData: GameEntity) {
        deleteAllGame()
        insertGame(*tagsData)
    }
}