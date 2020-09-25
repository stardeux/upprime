package com.stardeux.upprime.country.usecase

import android.net.Uri
import com.stardeux.upprime.country.usecase.model.AvailableCountry

class GetFlagUrlUseCase {

    //https://www.countryflags.io/be/flat/64.png
    operator fun invoke(availableCountry: AvailableCountry): Uri {
        return Uri.Builder()
            .scheme("https")
            .authority("www.countryflags.io")
            .appendPath(mapToApiIsoCode(availableCountry))
            .appendPath("flat")
            .appendPath("64.png")
            .build()
    }

    private fun mapToApiIsoCode (availableCountry: AvailableCountry): String {
        return when (availableCountry) {
            AvailableCountry.UNITED_STATES_AMERICA -> "us"
            AvailableCountry.GREAT_BRITAIN -> "gb"
            AvailableCountry.GERMANY -> "de"
            AvailableCountry.ARGENTINA -> "ar"
        }
    }

}