package com.stardeux.upprime.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.core.analytics.AnalyticsValues
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.analytics.getTrackingValue
import com.stardeux.upprime.core.remoteconf.RemoteConfWrapper
import com.stardeux.upprime.core.ui.SingleLiveEvent
import com.stardeux.upprime.country.usecase.SelectedUserCountryUseCase
import com.stardeux.upprime.country.usecase.model.AvailableCountry

class HomeViewModel(
    private val remoteConfWrapper: RemoteConfWrapper,
    availableCountry: AvailableCountry,
    analyticsWrapper: AnalyticsWrapper
    ) : ViewModel() {

    init {
        analyticsWrapper.setUserProperty(AnalyticsValues.UserProperty.COUNTRY, availableCountry.getTrackingValue())
    }

    private val _isInterstitialActivated = SingleLiveEvent<Boolean>().apply {
        value = remoteConfWrapper.isInterstitialActivated()
    }
    val isInterstitialActivated : LiveData<Boolean> = _isInterstitialActivated

    private val _displayInterstitial = SingleLiveEvent<Boolean>()
    val displayInterstitial : LiveData<Boolean> = _displayInterstitial


    fun onInterstitialLoaded() {
        _displayInterstitial.value = true
    }

}