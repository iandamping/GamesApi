package com.junemon.gamesapi.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.junemon.gamesapi.core.data.model.GameGenre


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class SharedViewModel: BaseViewModel() {

    private var _listGameGenre:MutableLiveData<List<GameGenre>> = MutableLiveData()
    val listGameGenre:LiveData<List<GameGenre>> = _listGameGenre

    fun setGames(data:List<GameGenre>){
        _listGameGenre.value = data
    }
}