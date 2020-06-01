package com.stardeux.upprime.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.core.remoteconf.RemoteConfWrapper
import com.stardeux.upprime.core.ui.SingleLiveEvent

class HomeViewModel(private val remoteConfWrapper: RemoteConfWrapper) : ViewModel() {

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