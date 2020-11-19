package com.junemon.gamesapi.core.data.datasource.remote

import androidx.paging.PagingSource
import com.junemon.gamesapi.core.data.datasource.remote.network.ApiInterface
import com.junemon.gamesapi.core.domain.model.Results
import com.junemon.gamesapi.core.domain.model.GameData
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Ian Damping on 20,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
private const val GAMEAPI_STARTING_PAGE_INDEX = 1

class GamePaginationRemoteDataSource (
    private val api: ApiInterface
) : PagingSource<Int, GameData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameData> {
        val position = params.key ?: GAMEAPI_STARTING_PAGE_INDEX
        return try {
            when (val response = oneShotCalls { api.getPaginationListGames(position) }) {
                is Results.Success -> {
                    LoadResult.Page(
                        data = response.data.data,
                        prevKey = if (position == GAMEAPI_STARTING_PAGE_INDEX) null else position - 1,
                        nextKey = if (response.data.data.isEmpty()) null else position + 1
                    )
                }
                is Results.Error -> {
                    LoadResult.Error(response.exception)
                }
            }

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


    private inline fun <T> oneShotCalls(call: () -> Response<T>): Results<T> {
        try {
            val response = call.invoke()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Results.Success(body)
                }else{
                    Results.Error(Exception("body is null"))
                }
            } else return Results.Error(Exception("response not success"))
        } catch (e: Exception) {
            return when(e){
                is IOException ->{
                    Results.Error(e)
                }
                is SocketTimeoutException ->{
                    Results.Error(e)
                }
                else ->{
                    Results.Error(e)
                }
            }
        }
    }
}