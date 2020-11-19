package com.junemon.gamesapi.core.data.datasource.remote.response


import com.google.gson.annotations.SerializedName


data class GameSearchResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("background_image")
    val backgroundImage: String = ""
)


