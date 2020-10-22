package com.junemon.gamesapi.core.data.model

import com.google.gson.annotations.SerializedName

data class GameResponse<T>(@field:SerializedName("results") val data: List<T>)