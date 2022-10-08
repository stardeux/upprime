package com.stardeux.upprime.country.usecase

import android.net.Uri
import com.stardeux.upprime.country.usecase.model.AvailableCountry

class GetFlagUrlUseCase {

    //https://countryflagsapi.com/svg/bra
    operator fun invoke(availableCountry: AvailableCountry): Uri {
        return Uri.Builder()
            .scheme("https")
            .authority("www.countryflagsapi.com")
            .appendPath("png")
            .appendPath(mapToApiIsoCode(availableCountry))
            .build()
    }

    private fun mapToApiIsoCode (availableCountry: AvailableCountry): String {
        return when (availableCountry) {
            AvailableCountry.UNITED_STATES_AMERICA -> "us"
            AvailableCountry.GREAT_BRITAIN -> "gb"
            AvailableCountry.GERMANY -> "de"
            AvailableCountry.ARGENTINA -> "ar"
            AvailableCountry.AUSTRALIA -> "au"
            AvailableCountry.BRAZIL -> "br"
            AvailableCountry.CANADA -> "ca"
            AvailableCountry.FRANCE -> "fr"
        }
    }

}