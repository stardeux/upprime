package com.stardeux.upprime.home.ui

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.core.analytics.AnalyticsValues
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.extension.exhaustive
import com.stardeux.upprime.core.model.ReleaseType
import com.stardeux.upprime.core.ui.SingleLiveEvent
import com.stardeux.upprime.core.ui.updateOnActiveLiveData
import com.stardeux.upprime.rate.usecase.RateAppAnswer
import com.stardeux.upprime.rate.usecase.RateAppUseCase

class ReleaseTabListingViewModel(
    private val rateAppUseCase: RateAppUseCase, private val analyticsWrapper: AnalyticsWrapper
) : ViewModel() {

    private val _displayRateApp = SingleLiveEvent<Boolean>()
    val displayRateApp: LiveData<Boolean> = _displayRateApp

    val isFavorableActionReached = updateOnActiveLiveData { rateAppUseCase.canDisplayRateApp() }

    fun onRateAppAnswer(rateAppAnswer: RateAppAnswer) {
        rateAppUseCase.setRateAppAnswer(rateAppAnswer)
        if (rateAppAnswer == RateAppAnswer.YES) {
            _displayRateApp.value = true
        }
    }

    fun onRateAppClicked() {
        rateAppUseCase.setRateAppAnswer(RateAppAnswer.YES)
        _displayRateApp.value = true
    }

    fun trackScreen(activity: Activity, releaseType: ReleaseType) {
        val screenValue = when (releaseType) {
            ReleaseType.NEW -> AnalyticsValues.Screen.NEW_RELEASE
            ReleaseType.EXPIRING -> AnalyticsValues.Screen.EXPIRED_RELEASE
        }.exhaustive

        analyticsWrapper.setCurrentScreen(activity, screenValue)
    }
}