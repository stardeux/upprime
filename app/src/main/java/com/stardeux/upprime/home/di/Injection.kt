package com.stardeux.upprime.home.di

import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.remoteconf.RemoteConfWrapper
import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.usecase.SelectedUserCountryUseCase
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.home.ui.HomeViewModel
import com.stardeux.upprime.home.ui.ReleaseTabListingViewModel
import com.stardeux.upprime.rate.usecase.RateAppUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { provideReleaseTabListingViewModel(get(), get()) }
    viewModel { provideHomeViewModel(get(), getUserScope().get(), get()) }
}

private fun provideReleaseTabListingViewModel(
    rateAppUseCase: RateAppUseCase, analyticsWrapper: AnalyticsWrapper
): ReleaseTabListingViewModel {
    return ReleaseTabListingViewModel(rateAppUseCase, analyticsWrapper)
}

private fun provideHomeViewModel(
    remoteConfWrapper: RemoteConfWrapper,
    availableCountry: AvailableCountry,
    analyticsWrapper: AnalyticsWrapper
): HomeViewModel {
    return HomeViewModel(remoteConfWrapper, availableCountry, analyticsWrapper)
}