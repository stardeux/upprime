package com.stardeux.upprime.media.expired.di

import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.media.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.media.expired.repository.api.ExpiredApi
import com.stardeux.upprime.media.expired.ui.ExpiredMediaViewModel
import com.stardeux.upprime.media.expired.usecase.GetExpiredMediaUseCase
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.series.usecase.GetImdbSeriesDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val expiredModule = module {
    single { provideExpiredApi(get((named(AMAZON_NAMED_QUALIFIER)))) }
    factory { provideExpiredRepository(get()) }
    factory { provideExpiredMediaViewModel(getUserScope().get(), get(), get()) }

    scope<AvailableCountry> {
        factory { provideExpiredUseCase(get(), get()) }
    }
}


private fun provideExpiredApi(retrofit: Retrofit): ExpiredApi {
    return retrofit.create(ExpiredApi::class.java)
}

private fun provideExpiredRepository(expiredApi: ExpiredApi): ExpiredMediaRepository {
    return ExpiredMediaRepository(expiredApi)
}

private fun provideExpiredUseCase(
    expiredMediaRepository: ExpiredMediaRepository,
    availableCountry: AvailableCountry
): GetExpiredMediaUseCase {
    return GetExpiredMediaUseCase(expiredMediaRepository, availableCountry)
}

private fun provideExpiredMediaViewModel(
    getExpiredMediaUseCase: GetExpiredMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
): ExpiredMediaViewModel {
    return ExpiredMediaViewModel(
        getExpiredMediaUseCase,
        getImdbMovieDetailsUseCase,
        getImdbSeriesDetailsUseCase
    )
}