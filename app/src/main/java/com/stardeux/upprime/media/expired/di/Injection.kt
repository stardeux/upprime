package com.stardeux.upprime.media.expired.di

import com.stardeux.upprime.media.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.media.expired.repository.api.ExpiredApi
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
    factory { provideExpiredUseCase(get()) }
    factory { provideExpiredMediaViewModel(get(), get(), get()) }
}


private fun provideExpiredApi(retrofit: Retrofit): ExpiredApi {
    return retrofit.create(ExpiredApi::class.java)
}

private fun provideExpiredRepository(expiredApi: ExpiredApi): ExpiredMediaRepository {
    return ExpiredMediaRepository(expiredApi)
}

private fun provideExpiredUseCase(expiredMediaRepository: ExpiredMediaRepository): GetExpiredMediaUseCase {
    return GetExpiredMediaUseCase(expiredMediaRepository)
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