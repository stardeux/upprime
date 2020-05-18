package com.stardeux.upprime.media.expired.di

import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.database.UpPrimeDatabase
import com.stardeux.upprime.media.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.media.expired.repository.api.ExpiredApi
import com.stardeux.upprime.media.expired.repository.api.ExpiredMediaRemoteDataSource
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaDao
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaLocalDataSource
import com.stardeux.upprime.media.expired.ui.ExpiredMediaViewModel
import com.stardeux.upprime.media.expired.usecase.GetExpiredMediaUseCase
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val expiredModule = module {
    single { provideExpiredApi(get((named(AMAZON_NAMED_QUALIFIER)))) }
    factory { provideExpiredRepository(get()) }
    factory { provideExpiredMediaViewModel(getUserScope().get(), get(), get()) }
    factory { provideExpiredMediaRemoteDataSource(get()) }

    factory { provideExpiredMediaDao(get()) }
    factory { provideExpiredMediaLocalDataSource(get()) }

    scope<AvailableCountry> {
        factory { provideExpiredUseCase(get(), get()) }
    }
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

private fun provideExpiredRepository(expiredApi: ExpiredApi): ExpiredMediaRepository {
    return ExpiredMediaRepository(expiredApi)
}

private fun provideExpiredUseCase(
    expiredMediaRepository: ExpiredMediaRepository, availableCountry: AvailableCountry
): GetExpiredMediaUseCase {
    return GetExpiredMediaUseCase(expiredMediaRepository, availableCountry)
}

private fun provideExpiredMediaViewModel(
    getExpiredMediaUseCase: GetExpiredMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
): ExpiredMediaViewModel {
    return ExpiredMediaViewModel(
        getExpiredMediaUseCase, getImdbMovieDetailsUseCase, getImdbSeriesDetailsUseCase
    )
}