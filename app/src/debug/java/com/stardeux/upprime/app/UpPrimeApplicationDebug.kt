package com.stardeux.upprime.app

import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.soloader.SoLoader
import org.koin.android.ext.android.get

class UpPrimeApplicationDebug : UpPrimeApplication() {

    override fun onCreate() {
        super.onCreate()

        initFlipper()
    }


    private fun initFlipper() {
        SoLoader.init(this, false)

        if (FlipperUtils.shouldEnableFlipper(this)) {
            get<FlipperClient>().start()
        }
    }
}