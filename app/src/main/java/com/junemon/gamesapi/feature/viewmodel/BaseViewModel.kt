package com.junemon.gamesapi.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * Created by Ian Damping on 10,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
abstract class BaseViewModel: ViewModel() {

    /**
     * Request a snackbar to display a string.
     *
     * This variable is private because we don't want to expose [MutableLiveData].
     *
     * MutableLiveData allows anyone to set a value, and [PlantListViewModel] is the only
     * class that should be setting values.
     */
    private val _snackbar = MutableLiveData<String?>()

    /**
     * Request a snackbar to display a string.
     */
    protected val snackbar: LiveData<String?>
        get() = _snackbar

    private val _progressBar = MutableLiveData<Boolean>(false)
    /**
     * Show a loading spinner if true
     */
     val progressBar: LiveData<Boolean>
        get() = _progressBar

     fun setupProgressBar(data:Boolean){
        _progressBar.value = data
    }


    /**
     * Called immediately after the UI shows the snackbar.
     */
     fun onSnackbarShown() {
        _snackbar.value = null
    }

    /**
     * Helper function to call a data load function with a loading spinner; errors will trigger a
     * snackbar.
     *
     * By marking [block] as [suspend] this creates a suspend lambda which can call suspend
     * functions.
     *
     * @param block lambda to actually load data. It is called in the viewModelScope. Before calling
     *              the lambda, the loading spinner will display. After completion or error, the
     *              loading spinner will stop.
     */
    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _progressBar.value = true
                block()
            } catch (error: Throwable) {
                _snackbar.value = error.message
            } finally {
                _progressBar.value = false
            }
        }
    }
}