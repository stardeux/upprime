package com.stardeux.upprime.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.stardeux.upprime.media.expired.di.expiredModule
import com.stardeux.upprime.media.latest.di.latestModule
import com.stardeux.upprime.core.di.coreModule
import com.stardeux.upprime.country.di.countryModule
import com.stardeux.upprime.database.di.databaseModule
import com.stardeux.upprime.movie.di.movieModule
import com.stardeux.upprime.network.okhttp.fullAmazonNetwork
import com.stardeux.upprime.network.okhttp.fullTmdbNetwork
import com.stardeux.upprime.series.di.seriesModule
import com.stardeux.upprime.splash.di.splashModule
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
             * Core
             */
            modules(coreModule)

            /**
             * Mid core
             */
            modules(flipperModule, fullAmazonNetwork, fullTmdbNetwork, tmdbModule, databaseModule)

            /**
             * Features
             */
            modules(splashModule, latestModule, expiredModule, countryModule, movieModule, seriesModule)
        }
    }
}