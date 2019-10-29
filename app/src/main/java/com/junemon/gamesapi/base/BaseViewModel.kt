package com.junemon.gamesapi.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


/**
 * Created by Ian Damping on 29,October,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
abstract class BaseViewModel : ViewModel() {
    protected var fetchingJob = Job()
    protected var uiScope = CoroutineScope(Dispatchers.Main + fetchingJob)
    protected val vmScope = viewModelScope
}