package com.stardeux.upprime.country.ui.model

import android.net.Uri
import androidx.annotation.StringRes
import com.stardeux.upprime.country.usecase.model.AvailableCountry

data class CountryUi (
    val availableCountry: AvailableCountry,
    @StringRes val nameResId: Int,
    val flagUrl: Uri,
    val onCountryClicked : (CountryUi) -> Unit
)