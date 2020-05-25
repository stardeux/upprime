package com.stardeux.upprime.core.extension

import android.view.Window
import android.view.WindowManager

fun Window.openKeyboard() {
    setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}