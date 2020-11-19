package com.junemon.gamesapi.core.domain.model


import com.google.gson.annotations.SerializedName

data class GamesItem(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
)



data class GameGenre(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("games")
    val games: List<GamesItem>?,
)


