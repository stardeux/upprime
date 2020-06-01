package com.stardeux.upprime.core.extension

import android.app.Activity
import android.util.DisplayMetrics
import com.google.android.gms.ads.AdSize

fun Activity.getAdaptiveAdSize(): AdSize {
    val display = windowManager.defaultDisplay
    val outMetrics = DisplayMetrics()
    display.getMetrics(outMetrics)

    val widthPixels = outMetrics.widthPixels.toFloat()
    val density = outMetrics.density

    val adWidth = (widthPixels / density).toInt()

    // Step 3 - Get adaptive ad size and return for setting on the ad view.
    return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
}