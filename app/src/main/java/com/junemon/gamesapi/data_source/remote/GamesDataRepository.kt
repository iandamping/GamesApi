package com.junemon.gamesapi.data_source.remote

import com.junemon.gamesapi.data.singleResultLiveData


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GamesDataRepository(private val remoteSource: GamesDataSource) {

    fun getListGames() = singleResultLiveData { remoteSource.getListGames() }
}