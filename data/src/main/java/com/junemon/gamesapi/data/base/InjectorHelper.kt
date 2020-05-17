package com.junemon.gamesapi.data.base

import org.koin.dsl.module

/**
 * Created by Ian Damping on 01,December,2019
 * Github https://github.com/iandamping
 * Indonesia.
 */
val baseResultModule = module {
    single { BaseResult() as ExtractResult }
}