package com.junemon.gamesapi.domain2.usecase

import com.junemon.gamesapi.domain2.model.CreatorData
import com.junemon.gamesapi.domain2.repository.CreatorRepository
import io.reactivex.Single
import io.reactivex.disposables.Disposable

/**
 * Created by Ian Damping on 11,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
class CreatorUseCase(private val repository: CreatorRepository) {
    fun get(): Single<List<CreatorData>> = repository.get()
}