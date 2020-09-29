package com.stardeux.upprime.core.usecase

import android.content.Intent
import android.content.pm.PackageManager

class IsIntentResolvableUseCase(
    private val packageManager: PackageManager
) {

    fun isResolvable(intent: Intent): Boolean {
        return intent.resolveActivity(packageManager) != null
    }

}