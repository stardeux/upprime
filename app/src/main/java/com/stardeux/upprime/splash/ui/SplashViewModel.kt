package com.stardeux.upprime.splash.ui

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.core.analytics.AnalyticsValues
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.country.usecase.SelectedUserCountryUseCase
import com.stardeux.upprime.core.ui.SingleLiveEvent

class SplashViewModel(
    private val selectedUserCountryUseCase: SelectedUserCountryUseCase,
    private val analyticsWrapper: AnalyticsWrapper
) : ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavigationEvent>().apply {
        value =
            if (selectedUserCountryUseCase.getSelectedCountry() == null) NavigationEvent.Countries else NavigationEvent.Home
    }
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    fun trackScreen(activity: Activity) {
        analyticsWrapper.setCurrentScreen(activity, AnalyticsValues.Screen.SPLASH_SCREEN)
    }

    sealed class NavigationEvent {
        object Home : NavigationEvent()
        object Countries : NavigationEvent()
    }

}