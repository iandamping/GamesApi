package com.junemon.gamesapi.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ian.app.helper.base.BaseViewModel
import com.ian.app.helper.data.ResultToConsume
import com.junemon.gamesapi.domain2.usecase.CreatorUseCase
import com.junemon.gamesapi.model.CreatorPresentation
import com.junemon.gamesapi.model.mapListToPresentation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ian Damping on 11,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class CreatorDataViewModel(private val repository: CreatorUseCase) : BaseViewModel() {
    private val _creatorData: MutableLiveData<ResultToConsume<List<CreatorPresentation>>> =
        MutableLiveData()
    val creatorData: LiveData<ResultToConsume<List<CreatorPresentation>>>
        get() = _creatorData
    private val compositeDisposable by lazy { CompositeDisposable() }

    fun getCreator() {
        compositeDisposable.add(repository.get().doOnSubscribe {
            _creatorData.postValue(ResultToConsume.loading())
        }.subscribeOn(Schedulers.io()).map {
            it.mapListToPresentation()
        }.subscribe({
            _creatorData.postValue(ResultToConsume.success(it))
        }, {
            _creatorData.postValue(ResultToConsume.error(it.message!!))
        }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}