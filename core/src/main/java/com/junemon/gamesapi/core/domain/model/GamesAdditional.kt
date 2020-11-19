package com.junemon.gamesapi.core.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 09,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class GenresItem(
    @SerializedName("name")
    val name: String = ""
)
