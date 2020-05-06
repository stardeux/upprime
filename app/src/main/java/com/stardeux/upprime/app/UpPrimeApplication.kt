package com.stardeux.upprime.app

import android.app.Application
import com.stardeux.upprime.latest.di.latestModule
import com.stardeux.upprime.network.okhttp.fullNetwork
import com.stardeux.upprime.tools.flipper.flipperModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

abstract class UpPrimeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

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
            modules(fullNetwork)

            /**
             * Features
             */
            modules(latestModule)
        }
    }
}