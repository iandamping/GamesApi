package com.junemon.gamesapi.core.network

import com.junemon.model.games.GameData
import com.junemon.model.games.GameDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Ian Damping on 02,April,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ApiInterface {
    @GET("games")
    suspend fun getListGames(): Response<com.junemon.model.games.GameResponse<GameData>>

    @GET("games/{games_id}")
    suspend fun getDetailGames(@Path("games_id") gamesId: Int): Response<GameDetail>
}