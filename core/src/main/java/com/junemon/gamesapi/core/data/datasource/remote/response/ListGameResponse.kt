package com.junemon.gamesapi.core.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class ListGameResponse<T>(@field:SerializedName("results") val data: List<T>)