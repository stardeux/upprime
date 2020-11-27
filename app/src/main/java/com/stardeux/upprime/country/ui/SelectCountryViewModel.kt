package com.stardeux.upprime.country.ui

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.core.analytics.AnalyticsValues
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.analytics.getTrackingParameters
import com.stardeux.upprime.core.analytics.getTrackingValue
import com.stardeux.upprime.country.ui.model.CountryUi
import com.stardeux.upprime.country.usecase.GetAvailableCountryUseCase
import com.stardeux.upprime.country.usecase.GetFlagUrlUseCase
import com.stardeux.upprime.country.ui.mapper.mapToCountryUi
import com.stardeux.upprime.country.usecase.SelectedUserCountryUseCase
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.core.ui.SingleLiveEvent

class SelectCountryViewModel(
    private val getAvailableCountryUseCase: GetAvailableCountryUseCase,
    private val selectedUserCountryUseCase: SelectedUserCountryUseCase,
    private val getFlagUrlUseCase: GetFlagUrlUseCase,
    private val analyticsWrapper: AnalyticsWrapper
) : ViewModel() {

    private val _countries = MutableLiveData<List<CountryUi>>().apply {
        value = getAvailableCountryUseCase().map(::toCountryUi)
    }
    val countries: LiveData<List<CountryUi>> = _countries


    private val _selectedCountry = SingleLiveEvent<CountryUi>()
    val selectedCountry: LiveData<CountryUi> = _selectedCountry

    private fun toCountryUi(availableCountry: AvailableCountry): CountryUi {
        return mapToCountryUi(
            availableCountry,
            getFlagUrlUseCase(availableCountry),
            ::onFlagClicked
        )
    }

    private fun onFlagClicked(countryUi: CountryUi) {
        analyticsWrapper.logEvent(AnalyticsValues.Event.COUNTRY_SELECTED, countryUi.getTrackingParameters())

        selectedUserCountryUseCase.setSelectedCountry(countryUi.availableCountry)
        _selectedCountry.value = countryUi
    }

    fun trackScreen(activity: Activity) {
        analyticsWrapper.setCurrentScreen(activity, AnalyticsValues.Screen.COUNTRY)
    }

}