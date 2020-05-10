package com.stardeux.upprime.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.stardeux.upprime.amazon.expired.di.expiredModule
import com.stardeux.upprime.amazon.latest.di.latestModule
import com.stardeux.upprime.country.di.countryModule
import com.stardeux.upprime.network.okhttp.fullAmazonNetwork
import com.stardeux.upprime.network.okhttp.fullTmdbNetwork
import com.stardeux.upprime.tmdb.di.tmdbModule
import com.stardeux.upprime.tools.flipper.flipperModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

abstract class UpPrimeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            // Android context
            androidContext(this@UpPrimeApplication)

            /**
             * Mid core
             */
            modules(flipperModule)
            modules(fullAmazonNetwork)
            modules(fullTmdbNetwork)
            modules(tmdbModule)

            /**
             * Features
             */
            modules(latestModule)
            modules(expiredModule)
            modules(countryModule)
        }
    }
}