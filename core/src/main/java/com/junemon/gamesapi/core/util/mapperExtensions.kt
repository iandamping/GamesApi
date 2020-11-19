package com.junemon.gamesapi.core.util

import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.data.datasource.remote.response.GameDetailResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameGenreResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameSearchResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GamesItemResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GenresItemResponse
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.model.GameGenre
import com.junemon.gamesapi.core.domain.model.GameSearch
import com.junemon.gamesapi.core.domain.model.GamesItem
import com.junemon.gamesapi.core.domain.model.GenresItem

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun GameResponse.mapRemoteDataToCache(): GameEntity = GameEntity(
    gameId = this.id,
    gameName = this.name,
    gameGenre = this.genres?.get(0)?.name,
    gameImage = this.backgroundImage
)

fun GameResponse.mapSingleRemoteGameDataToDomain(): Game = Game(
    gameId = this.id,
    gameName = this.name,
    gameGenre = this.genres?.get(0)?.name,
    gameImage = this.backgroundImage
)

fun List<GameResponse>.mapRemoteGameDataToDomain(): List<Game> =
    this.map { it.mapSingleRemoteGameDataToDomain() }

fun GameEntity.mapSingleEntitiesToDomain(): Game = Game(
    gameId = this.gameId,
    gameName = this.gameName,
    gameGenre = this.gameGenre,
    gameImage = this.gameImage
)

fun List<GameEntity>.mapEntitiesToDomain(): List<Game> =
    this.map { it.mapSingleEntitiesToDomain() }

fun GamesItemResponse.mapSingleGameItem(): GamesItem = GamesItem(name, id)

fun List<GamesItemResponse>.mapGameItem(): List<GamesItem> =
    this.map { it.mapSingleGameItem() }

fun GameGenreResponse.mapSingleRemoteGenreDataToDomain(): GameGenre = GameGenre(
    name = this.name,
    games = this.games?.mapGameItem()
)

fun List<GameGenreResponse>.mapRemoteGenresDataToDomain(): List<GameGenre> =
    this.map { it.mapSingleRemoteGenreDataToDomain() }

fun GenresItemResponse.mapSingleRemoteGenreItemDataToDomain():GenresItem = GenresItem(name)

fun List<GenresItemResponse>.mapRemoteGenreItemDataToDomain(): List<GenresItem> =
    this.map { it.mapSingleRemoteGenreItemDataToDomain() }



fun GameSearchResponse.mapSingleRemoteSearchDataToDomain(): GameSearch =
    GameSearch(id, name, backgroundImage)

fun List<GameSearchResponse>.mapRemoteSearchDataToDomain(): List<GameSearch> =
    this.map { it.mapSingleRemoteSearchDataToDomain() }

fun GameDetailResponse.mapSingleRemoteDetailGameDataToDomain(): GameDetail =
    GameDetail(backgroundImage, description, genres?.mapRemoteGenreItemDataToDomain(), rating, name)