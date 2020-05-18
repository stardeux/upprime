package com.stardeux.upprime.core.extension

import android.util.Log

fun Any.logDebug(debug: String) {
    Log.d("thilou " + javaClass.simpleName, debug)
}