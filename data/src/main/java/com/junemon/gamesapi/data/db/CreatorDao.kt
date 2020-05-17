package com.junemon.gamesapi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.junemon.gamesapi.data.datasource.model.CreatorDbEntity
import io.reactivex.Single

/**
 * Created by Ian Damping on 10,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Dao
interface CreatorDao {

    @Query("SELECT * FROM creator_table")
    fun loadCreator(): Single<List<CreatorDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCreator(vararg gameData: CreatorDbEntity)

    @Query("DELETE FROM creator_table")
    suspend fun deleteAllCreator()
}