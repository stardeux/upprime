package com.stardeux.upprime.media.latest.di

import android.content.SharedPreferences
import com.stardeux.upprime.media.latest.repository.LatestMediaRepository
import com.stardeux.upprime.media.latest.repository.api.LatestApi
import com.stardeux.upprime.media.latest.ui.LatestMediaViewModel
import com.stardeux.upprime.media.latest.usecase.GetLatestMediaUseCase
import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.database.UpPrimeDatabase
import com.stardeux.upprime.media.common.ui.model.MediaDetailsMapper
import com.stardeux.upprime.media.latest.repository.api.LatestMediaRemoteDataSource
import com.stardeux.upprime.media.latest.repository.database.LatestMediaDao
import com.stardeux.upprime.media.latest.repository.database.LatestMediaLocalDataSource
import com.stardeux.upprime.media.latest.repository.preferences.LatestMediaPreferences
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val latestModule = module {
    factory { provideLatestMediaDao(get()) }
    factory { provideLatestMediaLocalDataSource(get()) }
    factory { provideLatestMediaRemoteDataSource(get()) }
    factory { provideLatestMediaPreferences(get()) }

    single { provideLatestApi(get(named(AMAZON_NAMED_QUALIFIER))) }
    factory { provideLatestRepository(get(), get(), get()) }

    factory { provideLatestMediaViewModel(getUserScope().get(), get(), get(), get()) }

    scope<AvailableCountry> {
        factory { provideLatestUseCase(get(), get(), get()) }
    }
}

private fun provideLatestMediaRemoteDataSource(latestApi: LatestApi): LatestMediaRemoteDataSource {
    return LatestMediaRemoteDataSource(latestApi)
}

private fun provideLatestMediaLocalDataSource(latestMediaDao: LatestMediaDao): LatestMediaLocalDataSource {
    return LatestMediaLocalDataSource(latestMediaDao)
}

private fun provideLatestMediaDao(upPrimeDatabase: UpPrimeDatabase): LatestMediaDao {
    return upPrimeDatabase.latestMediaDao()
}

private fun provideLatestApi(retrofit: Retrofit): LatestApi {
    return retrofit.create(LatestApi::class.java)
}

private fun provideLatestRepository(
    latestMediaRemoteDataSource: LatestMediaRemoteDataSource,
    latestMediaLocalDataSource: LatestMediaLocalDataSource,
    latestMediaPreferences: LatestMediaPreferences
): LatestMediaRepository {
    return LatestMediaRepository(
        latestMediaRemoteDataSource, latestMediaLocalDataSource, latestMediaPreferences
    )
}

private fun provideLatestUseCase(
    latestMediaPreferences: LatestMediaPreferences,
    latestMediaRepository: LatestMediaRepository,
    availableCountry: AvailableCountry
): GetLatestMediaUseCase {
    return GetLatestMediaUseCase(latestMediaPreferences, latestMediaRepository, availableCountry)
}

private fun provideLatestMediaViewModel(
    getLatestMediaUseCase: GetLatestMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase,
    mediaDetailsMapper: MediaDetailsMapper
): LatestMediaViewModel {
    return LatestMediaViewModel(
        getLatestMediaUseCase,
        getImdbMovieDetailsUseCase,
        getImdbSeriesDetailsUseCase,
        mediaDetailsMapper
    )
}

private fun provideLatestMediaPreferences(sharedPreferences: SharedPreferences): LatestMediaPreferences {
    return LatestMediaPreferences(sharedPreferences)
}