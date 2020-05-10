package com.stardeux.upprime.country.usecase

import java.util.*

class GetMyCountryUseCase {

    operator fun invoke(): String {
        return Locale.getDefault().country
    }
}