package com.junemon.gamesapi.view

import com.junemon.gamesapi.core.model.GamesModel

/**
 * Created by Ian Damping on 16,May,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface MainView {

    fun onFailGetValue(e: Exception)

    fun onSuccessGetGame(data: List<GamesModel>)
}