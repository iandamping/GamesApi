package com.junemon.gamesapi.data.datasource.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.junemon.gamesapi.domain2.model.GameModel

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Entity(tableName = "game_table")
data class GameDbEntity(
    @PrimaryKey(autoGenerate = true) val localId:Int?,
    @ColumnInfo(name = "game_id")val gameID: Int,
    @ColumnInfo(name = "game_slug")val slug: String,
    @ColumnInfo(name = "game_released")val released: String,
    @ColumnInfo(name = "game_image")val backgroundImage: String,
    @ColumnInfo(name = "game_name")val name: String
)

fun GameDbEntity.mapToDomain(): GameModel = GameModel(gameID, slug, released, backgroundImage, name)
fun GameModel.mapToDatabase() = GameDbEntity(null,id, slug, released, backgroundImage, name)

fun List<GameDbEntity>.mapToDomain(): List<GameModel> = map { it.mapToDomain() }

fun List<GameModel>.mapToDatabase(): List<GameDbEntity> = map { it.mapToDatabase() }

fun LiveData<List<GameDbEntity>>.mapToDomain(): LiveData<List<GameModel>> = Transformations.map(this) { it.mapToDomain() }