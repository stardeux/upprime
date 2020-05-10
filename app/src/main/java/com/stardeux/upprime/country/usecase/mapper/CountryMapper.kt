package com.stardeux.upprime.country.usecase.mapper

import android.net.Uri
import com.stardeux.upprime.country.ui.model.CountryUi
import java.util.*

fun mapToCountryUi(locale: Locale, flagUrl : Uri, onCountryClicked : (CountryUi) -> Unit): CountryUi {
    return CountryUi(
        locale = locale,
        name = locale.displayCountry,
        flagUrl = flagUrl,
        onCountryClicked = onCountryClicked
    )
}