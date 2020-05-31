package com.stardeux.upprime.home.di

import com.stardeux.upprime.home.ui.ReleaseTabListingViewModel
import com.stardeux.upprime.rate.usecase.RateAppUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { provideReleaseTabListingViewModel(get()) }
}

private fun provideReleaseTabListingViewModel(rateAppUseCase: RateAppUseCase): ReleaseTabListingViewModel {
    return ReleaseTabListingViewModel(rateAppUseCase)
}