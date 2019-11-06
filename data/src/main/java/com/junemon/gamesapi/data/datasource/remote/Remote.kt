package com.junemon.gamesapi.data.datasource.remote

import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.data.datasource.model.GamesEntity
import com.junemon.gamesapi.data.datasource.model.ResultEntity
import com.junemon.gamesapi.data.datasource.remote.ApiConstant.baseUrl
import com.junemon.gamesapi.data.datasource.remote.ApiConstant.games
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

fun <T> flowResult(networkCall: suspend () -> ResultToConsume<T>): Flow<ResultToConsume<T>> =
    flow {
        emit(ResultToConsume.loading())
        val responseStatus = networkCall.invoke()
        when {
            responseStatus.data != null -> {
                if (responseStatus.status == ResultToConsume.Status.SUCCESS) {
                    emit(ResultToConsume.success(responseStatus.data!!))
                } else if (responseStatus.status == ResultToConsume.Status.ERROR) {
                    emit(ResultToConsume.error(networkCall.invoke().message!!))
                }
            }
            else -> {
                if (responseStatus.message != null) {
                    emit(ResultToConsume.error(responseStatus.message!!))
                } else emit(ResultToConsume.error("Unable to resolve host $baseUrl"))
            }
        }
    }