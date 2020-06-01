package com.stardeux.upprime.core.remoteconf

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class RemoteConfWrapper (private val firebaseRemoteConfig : FirebaseRemoteConfig) {

    init {
        with(firebaseRemoteConfig) {
            val configSettings =
                FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(3600).build()
            setConfigSettingsAsync(configSettings)

            setDefaultsAsync(mapOf(INTERSTITIAL_REMOTE_KEY to false))
            fetchAndActivate()
        }
    }


    fun isInterstitialAllowed() : Boolean {
        return firebaseRemoteConfig.getBoolean(INTERSTITIAL_REMOTE_KEY)
    }

    companion object {
        private const val INTERSTITIAL_REMOTE_KEY = ""
    }

}