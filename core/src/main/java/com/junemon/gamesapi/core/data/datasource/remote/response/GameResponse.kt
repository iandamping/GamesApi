package com.junemon.gamesapi.core.data.datasource.remote.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class GameResponse(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("genres")
    val genres: List<GenresItemResponse>?,
    @SerializedName("background_image")
    val backgroundImage: String = ""
)



