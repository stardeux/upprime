package com.stardeux.upprime.core.model

import android.content.Context
import com.stardeux.upprime.R

enum class ReleaseType {
    NEW,
    EXPIRING
}

fun ReleaseType.mapToString(context: Context): String {
    return when(this) {
        ReleaseType.NEW -> context.getString(R.string.release_type_new)
        ReleaseType.EXPIRING -> context.getString(R.string.release_type_expired)
    }
}