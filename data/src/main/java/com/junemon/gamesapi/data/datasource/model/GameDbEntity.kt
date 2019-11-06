package com.junemon.gamesapi.data.datasource.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.junemon.gamesapi.domain2.model.GameModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Entity(tableName = "game_table")
data class GameDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "game_id")val gameID: Int,
    @ColumnInfo(name = "game_slug")val slug: String,
    @ColumnInfo(name = "game_released")val released: String,
    @ColumnInfo(name = "game_image")val backgroundImage: String,
    @ColumnInfo(name = "game_name")val name: String
)

fun GameDbEntity.mapToDomain(): GameModel = GameModel(gameID, slug, released, backgroundImage, name)
fun GameModel.mapToDatabase() = GameDbEntity(id, slug, released, backgroundImage, name)

fun List<GameDbEntity>.mapToDomain(): List<GameModel> = map { it.mapToDomain() }
fun List<GameModel>.mapToDatabase(): List<GameDbEntity> = map { it.mapToDatabase() }

fun Flow<List<GameDbEntity>>.mapToDomain(): Flow<List<GameModel>> = map { it.mapToDomain() }