package com.junemon.gamesapi.core.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class DeveloperData(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("slug") val slug: String,
    @field:SerializedName("image_background") val imageBackground: String,
    @field:SerializedName("games") val listGames: List<DeveloperGameData>
)

data class DeveloperGameData(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("slug") val slug: String
)