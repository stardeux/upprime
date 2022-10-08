package com.stardeux.upprime.country.di

import android.content.SharedPreferences
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.country.repository.UserLocaleRepository
import com.stardeux.upprime.country.ui.SelectCountryViewModel
import com.stardeux.upprime.country.usecase.GetAvailableCountryUseCase
import com.stardeux.upprime.country.usecase.GetFlagUrlUseCase
import com.stardeux.upprime.country.usecase.SelectedUserCountryUseCase
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin

val USER_SCOPE_NAME = named("user_scope")

val countryModule = module {
    viewModel { provideSelectCountryViewModel(get(), get(), get(), get()) }
    factory { provideGetAvailableCountryUseCase() }
    factory { provideGetFlagUrlUseCase() }
    factory { provideSelectedUserLocaleUseCase(get()) }
    factory { provideUserLocaleRepository(get()) }

    scope(USER_SCOPE_NAME) {
        scoped<AvailableCountry> { provideSelectedAvailableCountry(get()) }
    }
}

private fun provideSelectedAvailableCountry(selectedUserCountryUseCase: SelectedUserCountryUseCase): AvailableCountry {
    return requireNotNull(selectedUserCountryUseCase.getSelectedCountry())
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
    getFlagUrlUseCase: GetFlagUrlUseCase,
    analyticsWrapper: AnalyticsWrapper
): SelectCountryViewModel {
    return SelectCountryViewModel(
        getAvailableCountryUseCase, selectedUserCountryUseCase, getFlagUrlUseCase, analyticsWrapper
    )
}

private fun provideGetAvailableCountryUseCase(): GetAvailableCountryUseCase {
    return GetAvailableCountryUseCase()
}

private fun provideGetFlagUrlUseCase(): GetFlagUrlUseCase {
    return GetFlagUrlUseCase()
}

fun getUserScope(): Scope {
    return getKoin().getOrCreateScope("user_scope", USER_SCOPE_NAME)
}