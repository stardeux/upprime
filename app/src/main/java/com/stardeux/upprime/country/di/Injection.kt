package com.stardeux.upprime.country.di

import com.stardeux.upprime.country.ui.SelectCountryViewModel
import com.stardeux.upprime.country.usecase.GetAvailableCountryUseCase
import com.stardeux.upprime.country.usecase.GetFlagUrlUseCase
import com.stardeux.upprime.country.usecase.GetMyCountryUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val countryModule = module {
    viewModel { provideSelectCountryViewModel(get(), get()) }
    factory { provideGetAvailableCountryUseCase() }
    factory { provideGetFlagUrlUseCase() }
    factory { provideGetMyCountryUseCase() }
}

private fun provideSelectCountryViewModel(
    getAvailableCountryUseCase: GetAvailableCountryUseCase, getFlagUrlUseCase: GetFlagUrlUseCase
): SelectCountryViewModel {
    return SelectCountryViewModel(getAvailableCountryUseCase, getFlagUrlUseCase)
}

private fun provideGetAvailableCountryUseCase(): GetAvailableCountryUseCase {
    return GetAvailableCountryUseCase()
}

private fun provideGetFlagUrlUseCase(): GetFlagUrlUseCase {
    return GetFlagUrlUseCase()
}

private fun provideGetMyCountryUseCase(): GetMyCountryUseCase {
    return GetMyCountryUseCase()
}