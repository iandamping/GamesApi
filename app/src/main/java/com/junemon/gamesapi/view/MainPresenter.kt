package com.junemon.gamesapi.view

import com.junemon.gamesapi.core.domain.usecase.GameUseCase
import com.junemon.gamesapi.core.model.ConsumeResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class MainPresenter(private val mView: MainView) : KoinComponent {
    private val repo: GameUseCase by inject()

    suspend fun getGames() {
        withContext(Dispatchers.Main){
            when (val response = repo.getListGames()) {
                is ConsumeResult.ConsumeData -> {
                    mView.onSuccessGetGame(response.data)
                }
                is ConsumeResult.ErrorHappen -> {
                    mView.onFailGetValue(response.exception)
                }
            }
        }
    }
}