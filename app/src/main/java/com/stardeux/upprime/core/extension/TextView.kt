package com.stardeux.upprime.core.extension

import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextAndVisibility(textStr: String?) {
    isVisible = textStr?.isNotBlank() == true
    text = textStr
}