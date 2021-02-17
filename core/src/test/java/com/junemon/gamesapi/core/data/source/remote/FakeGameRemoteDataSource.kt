package com.junemon.gamesapi.core.data.source.remote

import com.junemon.gamesapi.core.data.datasource.remote.GameRemoteDataSource
import com.junemon.gamesapi.core.data.datasource.remote.response.GameDetailResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameGenreResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameResponse
import com.junemon.gamesapi.core.data.datasource.remote.response.GameSearchResponse
import com.junemon.gamesapi.core.domain.model.DataHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Ian Damping on 01,December,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class FakeGameRemoteDataSource(
    val fakeGame: MutableList<GameResponse>? = mutableListOf(),
    val fakeGameGenre: MutableList<GameGenreResponse>? = mutableListOf(),
    val fakeGameDetail: MutableList<GameDetailResponse>? = mutableListOf(),
    val fakeGameSearch: MutableList<GameSearchResponse>? = mutableListOf(),
) :
    GameRemoteDataSource {
    override fun getFlowListGames(): Flow<DataHelper<List<GameResponse>>> {
        return flow {
            if (fakeGame != null) {
                emit(DataHelper.RemoteSourceValue(fakeGame))
            } else {
                emit(DataHelper.RemoteSourceEmpty)
            }
        }
    }

    override suspend fun getListGames(): DataHelper<List<GameResponse>> {
        return if (fakeGame != null) {
            DataHelper.RemoteSourceValue(fakeGame)
        } else {
            DataHelper.RemoteSourceEmpty
        }
    }

    override suspend fun getListGamesByGenres(): DataHelper<List<GameGenreResponse>> {
        return if (fakeGameGenre != null) {
            DataHelper.RemoteSourceValue(fakeGameGenre)
        } else {
            DataHelper.RemoteSourceEmpty
        }
    }

    override suspend fun getDetailGames(gameId: Int): DataHelper<GameDetailResponse> {
        val data = fakeGameDetail?.get(gameId)
        return if (data != null) {
            DataHelper.RemoteSourceValue(data)
        } else {
            DataHelper.RemoteSourceEmpty
        }
    }

    override suspend fun getSearchGames(query: String): DataHelper<List<GameSearchResponse>> {
        val data = fakeGameSearch?.filter { it.name == query }
        return if (!data.isNullOrEmpty()) {
            DataHelper.RemoteSourceValue(data)
        } else DataHelper.RemoteSourceEmpty
    }
}