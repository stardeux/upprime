package com.stardeux.upprime.splash.di

import com.stardeux.upprime.country.usecase.SelectedUserLocaleUseCase
import com.stardeux.upprime.splash.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { provideSplashViewModel(get()) }
}

private fun provideSplashViewModel(selectedUserLocaleUseCase: SelectedUserLocaleUseCase): SplashViewModel {
    return SplashViewModel(selectedUserLocaleUseCase)
}