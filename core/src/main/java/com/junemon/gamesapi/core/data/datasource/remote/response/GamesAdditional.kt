package com.junemon.gamesapi.core.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class GenresItemResponse(
    @SerializedName("name")
    val name: String = ""
)
