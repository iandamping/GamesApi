package com.junemon.gamesapi.core.network

import com.junemon.model.games.*
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
    suspend fun getListGames(): Response<GameResponse<GameData>>

    @GET("genres")
    suspend fun getListGamesByGenres(): Response<GameResponse<GameGenre>>

    @GET("games")
    suspend fun getSearchGames(@Query("search") searchQuery: String): Response<GameResponse<GameSearch>>

    @GET("games/{games_id}")
    suspend fun getDetailGames(@Path("games_id") gamesId: Int): Response<GameDetail>

    @GET("games")
    suspend fun getPaginationListGames(@Query("page") page: Int): Response<GameResponse<GameData>>
}