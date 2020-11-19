package com.junemon.gamesapi.core.domain.model

import com.google.gson.annotations.SerializedName

data class GameDetail(
    @SerializedName("background_image")
    val backgroundImage: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("genres")
    val genres: List<GenresItem>?,
    @SerializedName("rating")
    val rating: Double = 0.0,
    @SerializedName("name")
    val name: String = ""
)




