package com.junemon.gamesapi.core.data.model


import com.google.gson.annotations.SerializedName

data class GamesItem(
    @SerializedName("added")
    val added: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("slug")
    val slug: String = ""
)



data class GameGenre(
    @SerializedName("games_count")
    val gamesCount: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("games")
    val games: List<GamesItem>?,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image_background")
    val imageBackground: String = "",
    @SerializedName("slug")
    val slug: String = ""
)


