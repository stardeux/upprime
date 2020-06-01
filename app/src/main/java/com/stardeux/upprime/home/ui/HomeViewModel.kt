package com.stardeux.upprime.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.core.ui.SingleLiveEvent

class HomeViewModel : ViewModel() {

    private val _displayInterstitial = SingleLiveEvent<Boolean>()
    val displayInterstitial : LiveData<Boolean> = _displayInterstitial

    fun onInterstitialLoaded() {
        _displayInterstitial.value = true
    }

}