package com.junemon.gamesapi.core.domain.model

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class GamesItem(
    val name: String,
    val id: Int = 0
)

data class GameGenre(
    val name: String,
    val games: List<GamesItem>?
)