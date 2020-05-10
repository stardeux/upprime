package com.stardeux.upprime.country.ui.model

import android.net.Uri
import java.util.*

data class CountryUi (
    val locale: Locale,
    val name: String,
    val flagUrl: Uri,
    val onCountryClicked : (CountryUi) -> Unit
)