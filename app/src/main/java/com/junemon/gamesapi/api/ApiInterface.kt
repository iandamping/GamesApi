package com.junemon.gamesapi.api

import com.junemon.gamesapi.api.ApiConstant.games
import com.junemon.gamesapi.data.ResultListResponse
import com.junemon.gamesapi.data_source.remote.GamesModel
import retrofit2.Response
import retrofit2.http.GET


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ApiInterface {

    @GET(games)
    suspend fun getListGames(): Response<ResultListResponse<GamesModel>>
}