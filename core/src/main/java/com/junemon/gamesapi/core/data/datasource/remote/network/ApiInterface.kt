package com.junemon.gamesapi.core.data.datasource.remote.network

import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.model.GameGenre
import com.junemon.gamesapi.core.domain.model.GameRemoteData
import com.junemon.gamesapi.core.domain.model.GameSearch
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
    suspend fun getListGames(): Response<GameResponse<GameRemoteData>>

    @GET("genres")
    suspend fun getListGamesByGenres(): Response<GameResponse<GameGenre>>

    @GET("games")
    suspend fun getSearchGames(@Query("search") searchQuery: String): Response<GameResponse<GameSearch>>

    @GET("games/{games_id}")
    suspend fun getDetailGames(@Path("games_id") gamesId: Int): Response<GameDetail>

    @GET("games")
    suspend fun getPaginationListGames(@Query("page") page: Int): Response<GameResponse<com.junemon.gamesapi.core.domain.model.GameRemoteData>>
}