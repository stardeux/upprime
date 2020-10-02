package com.stardeux.upprime.media.expired.di

import android.content.SharedPreferences
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.database.UpPrimeDatabase
import com.stardeux.upprime.media.common.repository.model.mapper.ShortMediaMapper
import com.stardeux.upprime.media.common.ui.GetMediaItemUiUseCaseFacade
import com.stardeux.upprime.media.common.ui.model.MediaItemUiMapper
import com.stardeux.upprime.media.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.media.expired.repository.api.ExpiredApi
import com.stardeux.upprime.media.expired.repository.api.ExpiredMediaRemoteDataSource
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaDao
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaLocalDataSource
import com.stardeux.upprime.media.expired.repository.preferences.ExpiredMediaPreferences
import com.stardeux.upprime.media.expired.ui.ExpiredMediaViewModel
import com.stardeux.upprime.media.expired.usecase.GetExpiredMediaUseCase
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val expiredModule = module {
    single { provideExpiredApi(get((named(AMAZON_NAMED_QUALIFIER)))) }
    factory { provideExpiredRepository(get(), get(), get(), get()) }
    viewModel { provideExpiredMediaViewModel(getUserScope().get(), get(), get(), get()) }
    factory { provideExpiredMediaRemoteDataSource(get()) }
    factory { provideExpiredMediaPreferences(get()) }

    factory { provideExpiredMediaDao(get()) }
    factory { provideExpiredMediaLocalDataSource(get()) }

    scope<AvailableCountry> {
        factory { provideExpiredUseCase(get(), get(), get()) }
    }
}

private fun provideExpiredMediaPreferences(sharedPreferences: SharedPreferences): ExpiredMediaPreferences {
    return ExpiredMediaPreferences(sharedPreferences)
}

private fun provideExpiredMediaRemoteDataSource(expiredApi: ExpiredApi): ExpiredMediaRemoteDataSource {
    return ExpiredMediaRemoteDataSource(expiredApi)
}

private fun provideExpiredMediaLocalDataSource(expiredMediaDao: ExpiredMediaDao): ExpiredMediaLocalDataSource {
    return ExpiredMediaLocalDataSource(expiredMediaDao)
}

private fun provideExpiredMediaDao(upPrimeDatabase: UpPrimeDatabase): ExpiredMediaDao {
    return upPrimeDatabase.expiredMediaDao()
}

private fun provideExpiredApi(retrofit: Retrofit): ExpiredApi {
    return retrofit.create(ExpiredApi::class.java)
}

private fun provideExpiredRepository(
    shortMediaMapper: ShortMediaMapper,
    expiredMediaLocalDataSource: ExpiredMediaLocalDataSource,
    expiredMediaRemoteDataSource: ExpiredMediaRemoteDataSource,
    expiredMediaPreferences: ExpiredMediaPreferences
): ExpiredMediaRepository {
    return ExpiredMediaRepository(
        shortMediaMapper,
        expiredMediaLocalDataSource,
        expiredMediaRemoteDataSource,
        expiredMediaPreferences
    )
}

private fun provideExpiredUseCase(
    expiredMediaRepository: ExpiredMediaRepository,
    availableCountry: AvailableCountry,
    expiredMediaPreferences: ExpiredMediaPreferences
): GetExpiredMediaUseCase {
    return GetExpiredMediaUseCase(expiredMediaRepository, availableCountry, expiredMediaPreferences)
}

private fun provideExpiredMediaViewModel(
    getExpiredMediaUseCase: GetExpiredMediaUseCase,
    mediaItemUiMapper: MediaItemUiMapper,
    getMediaItemUiUseCaseFacade: GetMediaItemUiUseCaseFacade,
    analyticsWrapper: AnalyticsWrapper
): ExpiredMediaViewModel {
    return ExpiredMediaViewModel(
        getExpiredMediaUseCase,
        mediaItemUiMapper,
        getMediaItemUiUseCaseFacade,
        analyticsWrapper
    )
}