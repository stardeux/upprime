package com.stardeux.upprime.country.usecase

import com.stardeux.upprime.country.repository.UserLocaleRepository
import com.stardeux.upprime.country.usecase.model.AvailableCountry

class SelectedUserCountryUseCase(private val userLocaleRepository: UserLocaleRepository) {

    fun getSelectedCountry(): AvailableCountry? {
        val userLocaleStr = userLocaleRepository.getUserLocale()
        return AVAILABLE_COUNTRY_TEXT_MAP.find { it.second == userLocaleStr }?.first
    }

    fun setSelectedCountry(availableCountry: AvailableCountry) {
        val userLocaleStr = AVAILABLE_COUNTRY_TEXT_MAP.find { it.first == availableCountry }?.second
        userLocaleRepository.setUserLocale(requireNotNull(userLocaleStr))
    }

    companion object {
        private val AVAILABLE_COUNTRY_TEXT_MAP = listOf(
            AvailableCountry.GERMANY to "de",
            AvailableCountry.GREAT_BRITAIN to "gb",
            AvailableCountry.UNITED_STATES_AMERICA to "us"
        )
    }

}