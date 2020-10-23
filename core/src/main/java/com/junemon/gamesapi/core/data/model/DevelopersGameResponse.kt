package com.junemon.gamesapi.core.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class DevelopersGameResponse<T>(@field:SerializedName("results") val data: List<T>)