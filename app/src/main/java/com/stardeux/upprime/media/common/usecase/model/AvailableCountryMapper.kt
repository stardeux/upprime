package com.stardeux.upprime.media.common.usecase.model

import com.stardeux.upprime.country.usecase.model.AvailableCountry

val AvailableCountry.apiValue: String
    get() {
        return when (this) {
            AvailableCountry.UNITED_STATES_AMERICA -> "us"
            AvailableCountry.GREAT_BRITAIN -> "gb"
            AvailableCountry.GERMANY -> "de"
        }
    }
