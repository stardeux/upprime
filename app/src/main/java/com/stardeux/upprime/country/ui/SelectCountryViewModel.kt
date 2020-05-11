package com.stardeux.upprime.country.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.stardeux.upprime.country.ui.model.CountryUi
import com.stardeux.upprime.country.usecase.GetAvailableCountryUseCase
import com.stardeux.upprime.country.usecase.GetFlagUrlUseCase
import com.stardeux.upprime.country.ui.mapper.mapToCountryUi
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import fr.stardeux.autosc.util.SingleLiveEvent

class SelectCountryViewModel(
    private val getAvailableCountryUseCase: GetAvailableCountryUseCase,
    private val getFlagUrlUseCase: GetFlagUrlUseCase
) : ViewModel() {

    val countries = liveData {
        emit(getAvailableCountryUseCase().map(::toCountryUi))
    }

    private val _selectedCountry = SingleLiveEvent<CountryUi>()
    val selectedCountry: LiveData<CountryUi> = _selectedCountry

    private fun toCountryUi(availableCountry: AvailableCountry): CountryUi {
        return mapToCountryUi(availableCountry, getFlagUrlUseCase(availableCountry), ::onFlagClicked)
    }

    private fun onFlagClicked(countryUi: CountryUi) {
        _selectedCountry.value = countryUi
    }

}