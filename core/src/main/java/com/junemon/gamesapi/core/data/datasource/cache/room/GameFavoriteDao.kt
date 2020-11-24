package com.junemon.gamesapi.core.data.datasource.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.junemon.gamesapi.core.data.datasource.cache.entity.GameFavoriteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Dao
interface GameFavoriteDao {

    @Query("SELECT * FROM game_fav")
    fun loadFavoriteGame(): Flow<List<GameFavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(tagsData: GameFavoriteEntity)

    @Query("DELETE FROM GAME_FAV where game_favorite_id = :selectedId")
    suspend fun deleteFavoriteGameById(selectedId: Int)

}