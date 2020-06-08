package com.stardeux.upprime.core.analytics

import android.app.Activity
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.lang.Exception

class AnalyticsWrapper(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val firebaseCrashlytics: FirebaseCrashlytics) {

    fun setCurrentScreen(
        activity: Activity,
        @Size(min = 1L, max = 36L) screenName: String? = null
    ) {
        firebaseAnalytics.setCurrentScreen(activity, screenName, null)
    }

    fun recordException(throwable: Throwable) {
        firebaseCrashlytics.recordException(throwable)
    }

    fun logEvent(event: String, params: Bundle? = null) {
        firebaseAnalytics.logEvent(event, params)
    }

    fun trackError(@Size(min = 1L, max = 40L) key: String, args: Bundle? = null) {
        firebaseAnalytics.logEvent(key, args)
    }

    fun setUserProperty(
        @Size(min = 1L, max = 24L) key: String,
        @Nullable @Size(max = 36L) value: String
    ) {
        firebaseAnalytics.setUserProperty(key, value)
    }

}