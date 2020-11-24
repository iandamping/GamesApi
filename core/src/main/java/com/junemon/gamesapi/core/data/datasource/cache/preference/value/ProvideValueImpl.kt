package com.junemon.gamesapi.core.data.datasource.cache.preference.value

import com.junemon.gamesapi.core.data.datasource.cache.preference.PreferenceHelper
import com.junemon.gamesapi.core.data.datasource.cache.preference.key.ProvideKey
import java.util.*

/**
 * Created by Ian Damping on 24,November,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class ProvideValueImpl(
    private val provideKey: ProvideKey,
    private val preferenceHelper: PreferenceHelper
) : ProvideValue {

    override fun provideRoomPassPhraseValue(): String {
        return if (preferenceHelper.getStringInSharedPreference(provideKey.provideRoomPassPhraseKeys()) != "") {
            preferenceHelper.getStringInSharedPreference(provideKey.provideRoomPassPhraseKeys())
        } else {
            val randomValue = UUID.randomUUID().toString().replace("-", "")
            preferenceHelper.saveStringInSharedPreference(
                provideKey.provideRoomPassPhraseKeys(),
                randomValue
            ).let {
                preferenceHelper.getStringInSharedPreference(provideKey.provideRoomPassPhraseKeys())
            }
        }
    }

    override fun provideFavoriteUriValue(): String {
        return if (preferenceHelper.getStringInSharedPreference(provideKey.provideFavoriteUriKeys()) != "") {
            preferenceHelper.getStringInSharedPreference(provideKey.provideFavoriteUriKeys())
        } else {
            val value = "gamesapp://fav"
            preferenceHelper.saveStringInSharedPreference(
                provideKey.provideFavoriteUriKeys(),
                value
            ).let {
                preferenceHelper.getStringInSharedPreference(provideKey.provideFavoriteUriKeys())
            }
        }
    }
}