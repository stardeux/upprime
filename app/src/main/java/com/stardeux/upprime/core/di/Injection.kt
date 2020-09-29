package com.stardeux.upprime.core.di

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.preference.PreferenceManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.stardeux.upprime.BuildConfig
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.remoteconf.RemoteConfWrapper
import com.stardeux.upprime.core.usecase.IsIntentResolvableUseCase
import org.koin.dsl.module
import java.util.*


val coreModule = module {
    single { provideSharedPreference(get()) }
    factory { provideLocale() }

    single { provideFirebaseAnalytics(get()) }
    single { provideFirebaseCrashlytics() }
    single { provideAnalyticsWrapper(get(), get()) }

    single { provideFirebaseRemoteConfig() }
    single { provideRemoteConfWrapper(get()) } //Single to assure init routine once

    factory { providePackageManager(get()) }
    factory { provideIsIntentResolvableUseCase(get()) }
}

private fun provideSharedPreference(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

private fun provideLocale(): Locale {
    return Locale.getDefault()
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

private fun provideFirebaseAnalytics(context: Context): FirebaseAnalytics {
    return FirebaseAnalytics.getInstance(context)
}

private fun provideFirebaseCrashlytics(): FirebaseCrashlytics {
    return FirebaseCrashlytics.getInstance()
}

private fun providePackageManager(context: Context): PackageManager {
    return context.packageManager
}

private fun provideIsIntentResolvableUseCase(packageManager: PackageManager): IsIntentResolvableUseCase {
    return IsIntentResolvableUseCase(packageManager)
}