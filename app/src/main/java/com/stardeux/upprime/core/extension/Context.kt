package com.stardeux.upprime.core.extension

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.playStoreThisApp() = playStoreApp(packageName)

fun Context.playStoreApp(appPackageName: String) {
    startActivity(getPlayStoreIntentForApp(appPackageName))
}

fun Context.getPlayStoreIntentForApp(appPackageName: String): Intent {
    return Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")).run {
        if (resolveActivity(packageManager) == null) {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName"))
        } else {
            this
        }
    }
}