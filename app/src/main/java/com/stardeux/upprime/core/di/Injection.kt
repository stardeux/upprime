package com.stardeux.upprime.core.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.stardeux.upprime.BuildConfig
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.remoteconf.RemoteConfWrapper
import org.koin.dsl.module
import java.util.*


val coreModule = module {
    single { provideSharedPreference(get()) }
    factory { provideLocale() }

    single { provideAnalyticsWrapper(get(), get()) }
    single { provideFirebaseRemoteConfig() }
    single { provideRemoteConfWrapper(get()) } //Single to assure init routine once
}

private fun provideSharedPreference(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

private fun provideLocale(): Locale {
    return if (BuildConfig.DEBUG) Locale.US else Locale.getDefault()
}

private fun provideRemoteConfWrapper(firebaseRemoteConfig: FirebaseRemoteConfig): RemoteConfWrapper {
    return RemoteConfWrapper(firebaseRemoteConfig)
}

private fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
    return FirebaseRemoteConfig.getInstance()
}

private fun provideAnalyticsWrapper(
    firebaseAnalytics: FirebaseAnalytics, firebaseCrashlytics: FirebaseCrashlytics
): AnalyticsWrapper {
    return AnalyticsWrapper(firebaseAnalytics, firebaseCrashlytics)
}