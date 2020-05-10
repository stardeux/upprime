package com.stardeux.upprime.country.usecase

import java.util.*

class GetAvailableCountryUseCase {

    operator fun invoke(): List<Locale> {
        return listOf(Locale.US, Locale.UK, Locale.GERMANY)
    }
}