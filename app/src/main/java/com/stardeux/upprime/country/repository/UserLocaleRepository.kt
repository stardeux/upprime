package com.stardeux.upprime.country.repository

import android.content.SharedPreferences
import androidx.core.content.edit

class UserLocaleRepository (private val sharedPreferences: SharedPreferences) {

    fun getUserLocale() : String? {
        return sharedPreferences.getString(USER_LOCALE_KEY, null)
    }

    fun setUserLocale(userLocale: String) {
        return sharedPreferences.edit { putString(USER_LOCALE_KEY, userLocale) }
    }

    companion object {
        const val USER_LOCALE_KEY = "USER_LOCALE_KEY"
    }

}