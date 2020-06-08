package com.stardeux.upprime.home.ui

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stardeux.upprime.core.analytics.AnalyticsValues
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.analytics.getTrackingParameters
import com.stardeux.upprime.core.extension.exhaustive
import com.stardeux.upprime.core.model.ReleaseType
import com.stardeux.upprime.core.ui.SingleLiveEvent
import com.stardeux.upprime.core.ui.updateOnActiveLiveData
import com.stardeux.upprime.rate.usecase.RateAppAnswer
import com.stardeux.upprime.rate.usecase.RateAppUseCase

class ReleaseTabListingViewModel(
    private val rateAppUseCase: RateAppUseCase, private val analyticsWrapper: AnalyticsWrapper
) : ViewModel() {

    private val _event = SingleLiveEvent<Event>()
    val event: LiveData<Event> = _event

    val isFavorableActionReached = updateOnActiveLiveData { rateAppUseCase.canDisplayRateApp() }

    fun onRateAppAnswer(rateAppAnswer: RateAppAnswer) {
        analyticsWrapper.logEvent(
            AnalyticsValues.Event.RATE_APP_ANSWERED,
            rateAppAnswer.getTrackingParameters()
        )

        rateAppUseCase.setRateAppAnswer(rateAppAnswer)
        if (rateAppAnswer == RateAppAnswer.YES) {
            _event.value = Event.RateApp
        }
    }

    fun onPrivacyPolicyClicked() {
        analyticsWrapper.logEvent(AnalyticsValues.Event.PRIVACY_POLICY_CLICKED)
        _event.value = Event.PrivacyPolicy
    }

    fun onRateAppClicked() {
        analyticsWrapper.logEvent(AnalyticsValues.Event.RATE_APP_CLICKED)
        rateAppUseCase.setRateAppAnswer(RateAppAnswer.YES)
        _event.value = Event.RateApp
    }

    fun onSearchClicked() {
        analyticsWrapper.logEvent(AnalyticsValues.Event.SEARCH_CLICKED)
        _event.value = Event.Search
    }

    fun onCountryClicked() {
        analyticsWrapper.logEvent(AnalyticsValues.Event.COUNTRY_CLICKED)
        _event.value = Event.Country
    }

    fun onShareApp() {
        analyticsWrapper.logEvent(AnalyticsValues.Event.SHARE_APP_CLICKED)
        _event.value = Event.ShareApp
    }

    fun trackScreen(activity: Activity, releaseType: ReleaseType) {
        val screenValue = when (releaseType) {
            ReleaseType.NEW -> AnalyticsValues.Screen.NEW_RELEASE
            ReleaseType.EXPIRING -> AnalyticsValues.Screen.EXPIRED_RELEASE
        }.exhaustive

        analyticsWrapper.setCurrentScreen(activity, screenValue)
    }

    sealed class Event {
        object RateApp : Event()
        object Search : Event()
        object Country : Event()
        object ShareApp : Event()
        object PrivacyPolicy : Event()
    }
}