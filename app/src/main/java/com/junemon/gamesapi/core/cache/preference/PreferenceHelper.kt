package com.junemon.gamesapi.core.cache.preference

interface PreferenceHelper {

    fun registerListener(listenerClass:BaseSharedPreferenceListener)

    fun unregisterListener(listenerClass:BaseSharedPreferenceListener)

    fun saveStringInSharedPreference(key: String?, value: String?)

    fun getStringInSharedPreference(key: String?): String

    fun deleteSharedPreference(key: String)

    fun deleteAllSharedPrefrence()
}
