package com.stardeux.upprime.country.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.stardeux.upprime.country.usecase.GetAvailableCountryUseCase
import com.stardeux.upprime.country.usecase.GetFlagUrlUseCase
import com.stardeux.upprime.country.usecase.mapper.mapToCountryUi
import java.util.*

class SelectCountryViewModel(
    private val getAvailableCountryUseCase: GetAvailableCountryUseCase,
    private val getFlagUrlUseCase: GetFlagUrlUseCase
) : ViewModel() {

    val countries = liveData {
        emit(getAvailableCountryUseCase().map(::toCountryUi))
    }

    private val _selectedCountry = MutableLiveData<CountryUi>()
    val selectedCountry: LiveData<CountryUi> = _selectedCountry

    private fun toCountryUi(locale: Locale): CountryUi {
        return mapToCountryUi(locale, getFlagUrlUseCase(locale), ::onFlagClicked)
    }

    private fun onFlagClicked(countryUi: CountryUi) {
        _selectedCountry.value = countryUi
    }

}