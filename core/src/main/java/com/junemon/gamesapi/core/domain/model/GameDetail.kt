package com.junemon.gamesapi.core.domain.model

import com.junemon.gamesapi.core.data.datasource.remote.response.GenresItemResponse

/**
 * Created by Ian Damping on 19,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class GameDetail(
    val backgroundImage: String,
    val description: String,
    val genres: List<GenresItemResponse>?,
    val rating: Double,
    val name: String
)