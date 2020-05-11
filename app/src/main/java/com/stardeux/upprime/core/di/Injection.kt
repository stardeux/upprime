package com.stardeux.upprime.core.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.koin.dsl.module

val coreModule = module {
    single { provideSharedPreference(get()) }
}

private fun provideSharedPreference(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}