package com.stardeux.upprime.core.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.stardeux.upprime.BuildConfig
import org.koin.dsl.module
import java.util.*

val coreModule = module {
    single { provideSharedPreference(get()) }
    factory { provideLocale() }
}

private fun provideSharedPreference(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

private fun provideLocale(): Locale {
    return if (BuildConfig.DEBUG) Locale.US else Locale.getDefault()
}