package com.junemon.gamesapi.core.data.datasource.cache.preference.key

import com.junemon.gamesapi.core.BuildConfig
import com.junemon.gamesapi.core.data.datasource.cache.preference.PreferenceHelper
import java.util.*

/**
 * Created by Ian Damping on 24,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class ProvideKeyImpl(private val preferenceHelper: PreferenceHelper) : ProvideKey {

    override fun provideRoomPassPhraseKeys(): String {
        return if (preferenceHelper.getStringInSharedPreference(BuildConfig.passPhrase) != "") {
            preferenceHelper.getStringInSharedPreference(BuildConfig.passPhrase)
        } else {
            val randomValue = UUID.randomUUID().toString().replace("-", "")
            preferenceHelper.saveStringInSharedPreference(BuildConfig.passPhrase, randomValue).let {
                preferenceHelper.getStringInSharedPreference(BuildConfig.passPhrase)
            }
        }
    }

    override fun provideFavoriteUriKeys(): String {
        return if (preferenceHelper.getStringInSharedPreference(BuildConfig.uriFav) != "") {
            preferenceHelper.getStringInSharedPreference(BuildConfig.uriFav)
        } else {
            val randomValue = UUID.randomUUID().toString().replace("-", "")
            preferenceHelper.saveStringInSharedPreference(BuildConfig.uriFav, randomValue).let {
                preferenceHelper.getStringInSharedPreference(BuildConfig.uriFav)
            }
        }
    }
}