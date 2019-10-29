package com.junemon.gamesapi.data_source.remote

import com.junemon.gamesapi.api.ApiInterface
import com.junemon.gamesapi.base.BaseDataSource


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class GamesDataSource(private val api: ApiInterface) : BaseDataSource() {

    suspend fun getListGames() = getResult { api.getListGames() }
}