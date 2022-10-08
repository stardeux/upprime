package com.stardeux.upprime.country.usecase

import com.stardeux.upprime.country.repository.UserLocaleRepository
import com.stardeux.upprime.country.usecase.model.AvailableCountry

class SelectedUserCountryUseCase(private val userLocaleRepository: UserLocaleRepository) {

    private val serializedValue = AvailableCountry.values().map { Pair(it, getSerializedValue(it)) }

    fun getSelectedCountry(): AvailableCountry? {
        val userLocaleStr = userLocaleRepository.getUserLocale()
        return serializedValue.find { it.second == userLocaleStr }?.first
    }

    fun setSelectedCountry(availableCountry: AvailableCountry) {
        val userLocaleStr = serializedValue.find { it.first == availableCountry }?.second
        userLocaleRepository.setUserLocale(requireNotNull(userLocaleStr))
    }

    private fun getSerializedValue(availableCountry: AvailableCountry): String {
        return when(availableCountry) {
            AvailableCountry.UNITED_STATES_AMERICA -> "us"
            AvailableCountry.GREAT_BRITAIN -> "gb"
            AvailableCountry.GERMANY -> "de"
            AvailableCountry.ARGENTINA -> "ar"
            AvailableCountry.AUSTRALIA -> "au"
            AvailableCountry.BRAZIL -> "br"
            AvailableCountry.CANADA -> "ca"
            AvailableCountry.FRANCE -> "fr"
            AvailableCountry.INDIA -> "in"
            AvailableCountry.NETHERLANDS -> "nl"
        }
    }
}