package com.junemon.model.games

import com.google.gson.annotations.SerializedName

data class GameResponse<T>(@field:SerializedName("results") val data: List<T>)