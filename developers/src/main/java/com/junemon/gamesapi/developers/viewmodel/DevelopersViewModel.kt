package com.junemon.gamesapi.developers.viewmodel

import androidx.lifecycle.asLiveData
import com.junemon.gamesapi.core.domain.usecase.DevelopersUseCase
import com.junemon.gamesapi.feature.viewmodel.BaseViewModel

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DevelopersViewModel (private val repository: DevelopersUseCase) :
    BaseViewModel() {

    fun getListDevelopers() = repository.getListDevelopers().asLiveData()
}