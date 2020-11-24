package com.junemon.gamesapi.core.module

import com.junemon.gamesapi.core.data.datasource.cache.preference.key.ProvideKey
import com.junemon.gamesapi.core.data.datasource.cache.preference.key.ProvideKeyImpl
import com.junemon.gamesapi.core.data.datasource.cache.preference.value.ProvideValue
import com.junemon.gamesapi.core.data.datasource.cache.preference.value.ProvideValueImpl
import org.koin.dsl.module

/**
 * Created by Ian Damping on 24,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
val keyProviderModule = module {
    single<ProvideKey> { ProvideKeyImpl(get()) }
    single<ProvideValue> { ProvideValueImpl(get(), get()) }
}

