package com.junemon.gamesapi.core.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
    @SerializedName("background_image")
    val backgroundImage: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("genres")
    val genres: List<GenresItemResponse>?,
    @SerializedName("rating")
    val rating: Double = 0.0,
    @SerializedName("name")
    val name: String = ""
)




