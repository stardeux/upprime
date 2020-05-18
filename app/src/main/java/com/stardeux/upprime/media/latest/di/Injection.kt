package com.stardeux.upprime.media.latest.di

import com.stardeux.upprime.media.latest.repository.LatestMediaRepository
import com.stardeux.upprime.media.latest.repository.api.LatestApi
import com.stardeux.upprime.media.latest.ui.LatestMediaViewModel
import com.stardeux.upprime.media.latest.usecase.GetLatestMediaUseCase
import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.database.UpPrimeDatabase
import com.stardeux.upprime.media.latest.repository.api.LatestMediaRemoteDataSource
import com.stardeux.upprime.media.latest.repository.database.LatestMediaDao
import com.stardeux.upprime.media.latest.repository.database.LatestMediaLocalDataSource
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val latestModule = module {
    factory { provideLatestMediaDao(get()) }
    factory { provideLatestMediaDataSource(get()) }
    factory { provideLatestMediaRemoteDataSource(get()) }

    single { provideLatestApi(get(named(AMAZON_NAMED_QUALIFIER))) }
    factory { provideLatestRepository(get(), get()) }

    factory { provideLatestMediaViewModel(getUserScope().get(), get(), get()) }

    scope<AvailableCountry> {
        factory { provideLatestUseCase(get(), get()) }
    }
}

private fun provideLatestMediaRemoteDataSource(latestApi: LatestApi): LatestMediaRemoteDataSource {
    return LatestMediaRemoteDataSource(latestApi)
}

private fun provideLatestMediaDataSource(latestMediaDao: LatestMediaDao): LatestMediaLocalDataSource {
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
    latestMediaLocalDataSource: LatestMediaLocalDataSource
): LatestMediaRepository {
    return LatestMediaRepository(latestMediaRemoteDataSource, latestMediaLocalDataSource)
}

private fun provideLatestUseCase(
    latestMediaRepository: LatestMediaRepository, availableCountry: AvailableCountry
): GetLatestMediaUseCase {
    return GetLatestMediaUseCase(latestMediaRepository, availableCountry)
}

private fun provideLatestMediaViewModel(
    getLatestMediaUseCase: GetLatestMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
): LatestMediaViewModel {
    return LatestMediaViewModel(
        getLatestMediaUseCase, getImdbMovieDetailsUseCase, getImdbSeriesDetailsUseCase
    )
}