package com.stardeux.upprime.media.common.usecase.model

import com.stardeux.upprime.country.usecase.model.AvailableCountry

fun mapAvailableCountryToApiValue(availableCountry: AvailableCountry): String {
    return when(availableCountry) {
        AvailableCountry.UNITED_STATES_AMERICA -> "us"
        AvailableCountry.GREAT_BRITAIN -> "gb"
        AvailableCountry.GERMANY -> "de"
    }
}