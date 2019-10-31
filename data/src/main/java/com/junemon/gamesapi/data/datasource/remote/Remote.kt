package com.junemon.gamesapi.data.datasource.remote

import com.junemon.gamesapi.data.datasource.model.GamesEntity
import com.junemon.gamesapi.data.datasource.model.ResultEntity
import com.junemon.gamesapi.data.datasource.remote.ApiConstant.games
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GamesApi {

    @GET(games)
    suspend fun getGames(): Response<ResultEntity<GamesEntity>>
}

object ApiConstant {
    const val baseUrl = "https://api.rawg.io/api/"
    const val games = "games"
}