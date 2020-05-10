package com.stardeux.upprime.country.usecase

import java.util.*

class GetAvailableCountryUseCase {

    operator fun invoke(): List<String> {
        return listOf(Locale.US.country, Locale.UK.country, Locale.GERMANY.country)
    }
}