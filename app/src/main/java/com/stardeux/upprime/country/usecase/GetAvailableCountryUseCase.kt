package com.stardeux.upprime.country.usecase

import com.stardeux.upprime.country.usecase.model.AvailableCountry

class GetAvailableCountryUseCase {

    operator fun invoke(): List<AvailableCountry> {
        return AvailableCountry.values().toList()
    }
}