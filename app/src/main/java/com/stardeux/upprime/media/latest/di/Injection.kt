package com.stardeux.upprime.media.latest.di

import com.stardeux.upprime.media.latest.repository.LatestMediaRepository
import com.stardeux.upprime.media.latest.repository.api.LatestApi
import com.stardeux.upprime.media.latest.ui.LatestMediaViewModel
import com.stardeux.upprime.media.latest.usecase.GetLatestMediaUseCase
import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val latestModule = module {
    single { provideLatestApi(get(named(AMAZON_NAMED_QUALIFIER))) }
    factory { provideLatestRepository(get()) }

    factory { provideLatestMediaViewModel(getUserScope().get(), get(), get()) }

    scope<AvailableCountry> {
        factory { provideLatestUseCase(get(), get()) }
    }
}


private fun provideLatestApi(retrofit: Retrofit): LatestApi {
    return retrofit.create(LatestApi::class.java)
}

private fun provideLatestRepository(latestApi: LatestApi): LatestMediaRepository {
    return LatestMediaRepository(latestApi)
}

private fun provideLatestUseCase(
    latestMediaRepository: LatestMediaRepository,
    availableCountry: AvailableCountry
): GetLatestMediaUseCase {
    return GetLatestMediaUseCase(latestMediaRepository, availableCountry)
}

private fun provideLatestMediaViewModel(
    getLatestMediaUseCase: GetLatestMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
): LatestMediaViewModel {
    return LatestMediaViewModel(
        getLatestMediaUseCase,
        getImdbMovieDetailsUseCase,
        getImdbSeriesDetailsUseCase
    )
}