package com.stardeux.upprime.country.ui.mapper

import android.net.Uri
import com.stardeux.upprime.R
import com.stardeux.upprime.country.ui.model.CountryUi
import com.stardeux.upprime.country.usecase.model.AvailableCountry

fun mapToCountryUi(availableCountry: AvailableCountry, flagUrl : Uri, onCountryClicked : (CountryUi) -> Unit): CountryUi {
    return CountryUi(
        availableCountry = availableCountry,
        nameResId = mapToCountryNameResId(availableCountry),
        flagUrl = flagUrl,
        onCountryClicked = onCountryClicked
    )
}

fun mapToCountryNameResId(availableCountry: AvailableCountry): Int {
    return when(availableCountry) {
        AvailableCountry.UNITED_STATES_AMERICA -> R.string.country_us_name
        AvailableCountry.GREAT_BRITAIN -> R.string.country_uk_name
        AvailableCountry.GERMANY -> R.string.country_de_name
        AvailableCountry.ARGENTINA -> R.string.country_ar_name
    }
}