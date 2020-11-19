package com.junemon.gamesapi.core.domain.usecase

import androidx.paging.PagingData
import com.junemon.gamesapi.core.domain.model.GameData
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

    override fun getPagingListGames(): Flow<PagingData<GameData>> = repository.getPagingListGames()
}