package com.stardeux.upprime.core.extension

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.widget.NumberPicker
import androidx.annotation.ColorInt

fun NumberPicker.colorDivider(@ColorInt dividerColor: Int) {
    divider?.colorFilter = PorterDuffColorFilter(dividerColor, PorterDuff.Mode.SRC_IN)
}

val NumberPicker.divider: Drawable?
    get() {
        val dividerField =
            NumberPicker::class.java.declaredFields.firstOrNull { it.name == "mSelectionDivider" }
        return dividerField?.let {
            try {
                it.isAccessible = true
                it.get(this) as Drawable
            } catch (e: Exception) {
                null
            }
        }
    }