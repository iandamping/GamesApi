package com.junemon.gamesapi.core.domain.model

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class Game(
    val gameName: String,
    val gameId: Int,
    val gameGenre: String?,
    val gameImage: String
)
