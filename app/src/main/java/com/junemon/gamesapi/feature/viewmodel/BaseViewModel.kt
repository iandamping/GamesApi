package com.junemon.gamesapi.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junemon.gamesapi.util.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
abstract class BaseViewModel: ViewModel() {

    private val _progressBar = MutableLiveData<Event<Boolean>>()
    /**
     * Show a loading spinner if true
     */
     val progressBar: LiveData<Event<Boolean>>
        get() = _progressBar

     fun setupProgressBar(data:Boolean){
        _progressBar.value = Event(data)
    }


}