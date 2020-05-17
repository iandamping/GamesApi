package com.junemon.gamesapi.domain2.repository

import androidx.lifecycle.LiveData
import com.junemon.gamesapi.domain2.model.GameData
import com.junemon.model.RepositoryDataHelper

/**
 * Created by Ian Damping on 31,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface GamesRepository {

    fun get(): LiveData<RepositoryDataHelper<List<GameData>>>
}