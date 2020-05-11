package com.stardeux.upprime.splash.di

import com.stardeux.upprime.country.usecase.SelectedUserCountryUseCase
import com.stardeux.upprime.splash.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { provideSplashViewModel(get()) }
}

private fun provideSplashViewModel(selectedUserCountryUseCase: SelectedUserCountryUseCase): SplashViewModel {
    return SplashViewModel(selectedUserCountryUseCase)
}