package com.junemon.gamesapi.core.cache

import android.app.Application
import android.content.Context
import com.junemon.gamesapi.util.AppConstant.prefHelperInit

class PreferenceHelper(app: Application) {


    private val prefHelp by lazy {
        app.getSharedPreferences(prefHelperInit, Context.MODE_PRIVATE)
    }
    private val preHelperEditor = prefHelp.edit()

    fun saveStringInSharedPreference(key: String?, value: String?) {
        preHelperEditor.putString(key, value).apply()
    }

    fun getStringInSharedPreference(key: String?): String? {
        return prefHelp.getString(key, "")
    }

    fun saveIntInSharedPreference(key: String?, value: Int?) {
        if (value != null) {
            preHelperEditor.putInt(key, value).apply()
        }
    }

    fun getIntInSharedPreference(key: String?): Int? {
        return prefHelp.getInt(key, 0)
    }

    fun saveBooleanInSharedPreference(key: String, value: Boolean) {
        preHelperEditor.putBoolean(key, value).apply()
    }

    fun getBooleanInSharedPreference(key: String): Boolean {
        return prefHelp.getBoolean(key, false)
    }

    fun deleteSharedPreference(key: String) {
        preHelperEditor.remove(key).apply()
    }

    fun deleteAllSharedPrefrence() {
        preHelperEditor.clear().apply()
    }
}