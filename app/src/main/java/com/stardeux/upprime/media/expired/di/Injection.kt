package com.stardeux.upprime.media.expired.di

import android.content.SharedPreferences
import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.database.UpPrimeDatabase
import com.stardeux.upprime.media.common.ui.model.MediaDetailsMapper
import com.stardeux.upprime.media.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.media.expired.repository.api.ExpiredApi
import com.stardeux.upprime.media.expired.repository.api.ExpiredMediaRemoteDataSource
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaDao
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaLocalDataSource
import com.stardeux.upprime.media.expired.repository.preferences.ExpiredMediaPreferences
import com.stardeux.upprime.media.expired.ui.ExpiredMediaViewModel
import com.stardeux.upprime.media.expired.usecase.GetExpiredMediaUseCase
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val expiredModule = module {
    single { provideExpiredApi(get((named(AMAZON_NAMED_QUALIFIER)))) }
    factory { provideExpiredRepository(get(), get(), get()) }
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
    expiredMediaLocalDataSource: ExpiredMediaLocalDataSource,
    expiredMediaRemoteDataSource: ExpiredMediaRemoteDataSource,
    expiredMediaPreferences: ExpiredMediaPreferences
): ExpiredMediaRepository {
    return ExpiredMediaRepository(
        expiredMediaLocalDataSource, expiredMediaRemoteDataSource, expiredMediaPreferences
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
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase,
    mediaDetailsMapper: MediaDetailsMapper
): ExpiredMediaViewModel {
    return ExpiredMediaViewModel(
        getExpiredMediaUseCase, getImdbMovieDetailsUseCase, getImdbSeriesDetailsUseCase, mediaDetailsMapper
    )
}