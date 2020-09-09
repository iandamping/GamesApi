package com.junemon.gamesapi.core.network

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Ian Damping on 02,April,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ApiInterface {
    @GET("games")
    suspend fun getListGames(): Response<com.junemon.model.games.GameResponse<com.junemon.model.games.GameData>>
}