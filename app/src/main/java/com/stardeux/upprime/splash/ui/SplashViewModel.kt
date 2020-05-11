package com.stardeux.upprime.splash.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.country.usecase.SelectedUserCountryUseCase
import fr.stardeux.autosc.util.SingleLiveEvent

class SplashViewModel (private val selectedUserCountryUseCase: SelectedUserCountryUseCase) : ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavigationEvent>().apply {
        value = if (selectedUserCountryUseCase.getSelectedCountry() != null) NavigationEvent.Countries else NavigationEvent.Home
    }
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent


    sealed class NavigationEvent {
        object Home : NavigationEvent()
        object Countries : NavigationEvent()
    }

}