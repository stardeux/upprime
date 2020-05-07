package com.stardeux.upprime.core.extension

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout

fun ViewGroup.setLayout(@LayoutRes layoutId: Int) {
    ConstraintLayout.inflate(context, layoutId, this)
}