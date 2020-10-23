package com.junemon.gamesapi.core.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Ian Damping on 20,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = "game_id") var gameId: Int,
    @ColumnInfo(name = "game_name") var gameName: String,
    @ColumnInfo(name = "game_genre") var gameGenre: String?,
    @ColumnInfo(name = "game_image") var gameImage: String
)