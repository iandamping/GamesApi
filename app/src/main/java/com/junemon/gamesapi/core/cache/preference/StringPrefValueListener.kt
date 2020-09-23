package com.junemon.gamesapi.core.cache.preference

import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Ian Damping on 23,September,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
class StringPrefValueListener : BaseSharedPreferenceListener() {

    private val _stringPreferenceValue: MutableStateFlow<String?> = MutableStateFlow(null)

    val stringPreferenceValue: StateFlow<String?> = _stringPreferenceValue

    fun setListenKey(key: String) {
        setKey(key)
    }

    fun destroyListenKey() {
        destroyKey()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        sharedPreferences?.let {
            if (getKey() != "") {
                if (key.equals(getKey())) {
                    _stringPreferenceValue.value = it.getString(getKey(), "") ?: ""
                }
            }
        }
    }
}