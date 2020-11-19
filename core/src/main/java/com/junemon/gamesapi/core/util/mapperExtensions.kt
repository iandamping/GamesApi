package com.junemon.gamesapi.core.util

import com.junemon.gamesapi.core.data.datasource.cache.entity.GameEntity
import com.junemon.gamesapi.core.domain.model.GameRemoteData

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
fun GameRemoteData.mapRemoteDataToCache():GameEntity = GameEntity(
    gameId = this.id,
    gameName = this.name,
    gameGenre = this.genres?.get(0)?.name,
    gameImage = this.backgroundImage
)