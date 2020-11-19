package com.junemon.gamesapi.core.domain.model


import com.google.gson.annotations.SerializedName


data class GameSearch(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("background_image")
    val backgroundImage: String = ""
)


