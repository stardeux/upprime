package com.stardeux.upprime.country.ui.model

import java.util.*

data class CountryItemUi (
    val locale: Locale,
    val name: String,
    val flagUrl: String,
    val onCountryClicked : (CountryItemUi) -> Unit
)