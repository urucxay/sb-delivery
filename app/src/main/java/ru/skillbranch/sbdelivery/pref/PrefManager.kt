package ru.skillbranch.sbdelivery.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PrefManager(context: Context) {

    private var lastModified by PrefDelegate(DEFAULT_DATE)

    val preferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(
            context
        )
    }

    fun clearAll() {
        preferences.all.clear()
    }

    fun setLastSyncDate(date: String) {
        lastModified = date
    }

    fun getLastSyncDate(): String = lastModified ?: DEFAULT_DATE

    companion object {
        private const val DEFAULT_DATE = "Wed, 21 Oct 2015 07:28:00 GMT"
    }

}