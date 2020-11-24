package com.junemon.gamesapi.core.data.datasource.remote.network

import com.junemon.gamesapi.core.data.datasource.remote.response.ListGameResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameDetailResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ian Damping on 02,April,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface ApiInterface {
    @GET("games")
    suspend fun getListGames(): Response<ListGameResponse<GameResponse>>

    @GET("games")
    suspend fun getSearchGames(@Query("search") searchQuery: String): Response<ListGameResponse<GameSearchResponse>>

    @GET("games/{games_id}")
    suspend fun getDetailGames(@Path("games_id") gamesId: Int): Response<GameDetailResponse>

    @GET("games")
    suspend fun getPaginationListGames(@Query("page") page: Int): Response<ListGameResponse<GameResponse>>
}