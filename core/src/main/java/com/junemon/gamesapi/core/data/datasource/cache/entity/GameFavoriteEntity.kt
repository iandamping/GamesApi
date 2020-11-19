package com.junemon.gamesapi.core.data.datasource.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
@Entity(tableName = "game_fav")
data class GameFavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "game_favorite_id")var gameFavoriteId: Int?,
    @ColumnInfo(name = "game_name") var gameName: String,
    @ColumnInfo(name = "game_description") var gameDescription: String,
    @ColumnInfo(name = "game_genre") var gameGenre: String?,
    @ColumnInfo(name = "game_image") var gameImage: String?,
    @ColumnInfo(name = "game_rating") var gameRating: String?,
)