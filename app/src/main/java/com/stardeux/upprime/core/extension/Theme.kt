package com.stardeux.upprime.core.extension

import android.content.res.Resources
import android.util.TypedValue

fun Resources.Theme.rippleResource(): Int {
    val outValue = TypedValue()
    resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
    return outValue.resourceId
}
