package com.junemon.gamesapi.domain2.repository

import com.junemon.gamesapi.domain2.model.CreatorData
import io.reactivex.Single
import io.reactivex.disposables.Disposable

/**
 * Created by Ian Damping on 10,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface CreatorRepository {
    fun get(): Single<List<CreatorData>>
}