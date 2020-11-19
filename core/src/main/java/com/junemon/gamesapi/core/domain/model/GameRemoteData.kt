package com.junemon.gamesapi.core.domain.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class GameRemoteData(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("genres")
    val genres: List<GenresItem>?,
    @SerializedName("background_image")
    val backgroundImage: String = ""
)



