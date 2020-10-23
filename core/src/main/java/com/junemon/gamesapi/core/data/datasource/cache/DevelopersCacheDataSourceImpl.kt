package com.junemon.gamesapi.core.data.datasource.cache

import com.junemon.gamesapi.core.cache.preference.PreferenceHelper
import com.junemon.gamesapi.core.data.data.datasource.DevelopersCacheDataSource

/**
 * Created by Ian Damping on 22,October,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class DevelopersCacheDataSourceImpl (private val preferenceHelper: PreferenceHelper) :
    DevelopersCacheDataSource {
    override fun saveStringInSharedPreference(key: String, value: String?) {
        preferenceHelper.saveStringInSharedPreference(key, value)
    }

    override fun getStringInSharedPreference(key: String): String {
        return preferenceHelper.getStringInSharedPreference(key)
    }

    override fun saveIntInSharedPreference(key: String, value: Int?) {
        preferenceHelper.saveIntInSharedPreference(key, value)
    }

    override fun getIntInSharedPreference(key: String): Int {
        return preferenceHelper.getIntInSharedPreference(key)
    }

    override fun saveBooleanInSharedPreference(key: String, value: Boolean) {
        preferenceHelper.saveBooleanInSharedPreference(key, value)
    }

    override fun getBooleanInSharedPreference(key: String): Boolean {
        return preferenceHelper.getBooleanInSharedPreference(key)
    }

    override fun deleteSharedPreference(key: String) {
        preferenceHelper.deleteSharedPreference(key)
    }

    override fun deleteAllSharedPrefrence() {
        preferenceHelper.deleteAllSharedPrefrence()
    }
}