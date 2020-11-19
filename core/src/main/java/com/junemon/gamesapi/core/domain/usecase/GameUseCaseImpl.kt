package com.junemon.gamesapi.core.domain.usecase

import androidx.paging.PagingData
import com.junemon.gamesapi.core.domain.model.Game
import com.junemon.gamesapi.core.domain.model.GameDetail
import com.junemon.gamesapi.core.domain.model.GameFavorite
import com.junemon.gamesapi.core.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameUseCaseImpl(private val repository: GameRepository) : GameUseCase {

    override fun getCachedListGames() = repository.getCachedListGames()

    override fun getListGamesByGenres() = repository.getListGamesByGenres()

    override fun getSearchGames(query: String) = repository.getSearchGames(query)

    override fun getDetailGames(gameId: Int) = repository.getDetailGames(gameId)

    override fun getPagingListGames(): Flow<PagingData<Game>> = repository.getPagingListGames()

    override suspend fun saveFavoriteGames(data: GameDetail) = repository.saveFavoriteGames(data)

    override fun getFavoriteGames():Flow<List<GameFavorite>> = repository.getFavoriteGames()

    override suspend fun clearFavoriteGameById(id: Int) = repository.clearFavoriteGameById(id)
}