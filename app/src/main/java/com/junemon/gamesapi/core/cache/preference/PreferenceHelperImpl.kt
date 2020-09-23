package com.junemon.gamesapi.core.cache.preference

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by Ian Damping on 23,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class PreferenceHelperImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    PreferenceHelper {

    private val preHelperEditor: SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    override fun registerListener(listenerClass:BaseSharedPreferenceListener) {
       sharedPreferences.registerOnSharedPreferenceChangeListener(listenerClass)
    }

    override fun unregisterListener(listenerClass:BaseSharedPreferenceListener) {
       sharedPreferences.unregisterOnSharedPreferenceChangeListener(listenerClass)
    }

    override fun saveStringInSharedPreference(key: String?, value: String?) {
        preHelperEditor.putString(key, value).apply()
    }

    override fun getStringInSharedPreference(key: String?): String {
        return sharedPreferences.getString(key,"") ?: ""
    }

    override fun deleteSharedPreference(key: String) {
        preHelperEditor.remove(key).apply()
    }

    override fun deleteAllSharedPrefrence() {
        preHelperEditor.clear().apply()
    }
}