package com.junemon.gamesapi.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.junemon.gamesapi.core.util.Event

/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
abstract class BaseViewModel: ViewModel() {

    private val _progressBar = MutableLiveData<Event<Boolean>>()
    /**
     * Show a loading shimmer if true
     */
     val progressBar: LiveData<Event<Boolean>>
        get() = _progressBar

     fun setupProgressBar(data:Boolean){
        _progressBar.value = Event(data)
    }


}