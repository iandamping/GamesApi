package com.junemon.gamesapi.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.junemon.gamesapi.core.domain.repository.GameRepository
import com.junemon.model.ConsumeResult
import com.junemon.model.GenericPair
import com.junemon.model.games.GameData
import com.junemon.model.games.GameDetail
import com.junemon.model.games.GameGenre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GameUseCase @Inject constructor(private val repository: GameRepository) {

    fun getListGames(): LiveData<ConsumeResult<GameData>> = repository.getListGames().asLiveData()

    fun getListGamesByGenres(): LiveData<ConsumeResult<GameGenre>> = repository.getListGamesByGenres().asLiveData()

    fun getGenreAndGames() = repository.getGenreAndGames().asLiveData()

    fun getSearchGames(query:String) = repository.getSearchGames(query).asLiveData()

    fun getDetailGames(gameId:Int): LiveData<ConsumeResult<GameDetail>> = repository.getDetailGames(gameId).asLiveData()

    fun saveGames(data: GameData) = repository.saveGames(data)

    fun getCachedGames(): GameData = repository.getCachedGames()

}