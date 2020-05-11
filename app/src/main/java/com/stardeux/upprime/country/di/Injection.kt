package com.stardeux.upprime.country.di

import android.content.SharedPreferences
import com.stardeux.upprime.country.repository.UserLocaleRepository
import com.stardeux.upprime.country.ui.SelectCountryViewModel
import com.stardeux.upprime.country.usecase.GetAvailableCountryUseCase
import com.stardeux.upprime.country.usecase.GetFlagUrlUseCase
import com.stardeux.upprime.country.usecase.SelectedUserCountryUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val countryModule = module {
    viewModel { provideSelectCountryViewModel(get(), get(), get()) }
    factory { provideGetAvailableCountryUseCase() }
    factory { provideGetFlagUrlUseCase() }

    factory { provideUserLocaleRepository(get()) }
    factory { provideSelectedUserLocaleUseCase(get()) }
}

private fun provideSelectedUserLocaleUseCase(userLocaleRepository: UserLocaleRepository): SelectedUserCountryUseCase {
    return SelectedUserCountryUseCase(userLocaleRepository)
}

private fun provideUserLocaleRepository(sharedPreferences: SharedPreferences): UserLocaleRepository {
    return UserLocaleRepository(sharedPreferences)
}

private fun provideSelectCountryViewModel(
    getAvailableCountryUseCase: GetAvailableCountryUseCase,
    selectedUserCountryUseCase: SelectedUserCountryUseCase,
    getFlagUrlUseCase: GetFlagUrlUseCase
): SelectCountryViewModel {
    return SelectCountryViewModel(
        getAvailableCountryUseCase,
        selectedUserCountryUseCase,
        getFlagUrlUseCase
    )
}

private fun provideGetAvailableCountryUseCase(): GetAvailableCountryUseCase {
    return GetAvailableCountryUseCase()
}

private fun provideGetFlagUrlUseCase(): GetFlagUrlUseCase {
    return GetFlagUrlUseCase()
}