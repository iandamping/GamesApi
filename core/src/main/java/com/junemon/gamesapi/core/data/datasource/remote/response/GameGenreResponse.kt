package com.junemon.gamesapi.core.data.datasource.remote.response


import com.google.gson.annotations.SerializedName

data class GamesItemResponse(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
)
data class GameGenreResponse(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("games")
    val games: List<GamesItemResponse>?,
)





