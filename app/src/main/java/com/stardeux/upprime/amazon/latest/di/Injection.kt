package com.stardeux.upprime.amazon.latest.di

import com.stardeux.upprime.amazon.latest.repository.LatestRepository
import com.stardeux.upprime.amazon.latest.repository.api.LatestApi
import com.stardeux.upprime.amazon.latest.ui.LatestMediaViewModel
import com.stardeux.upprime.amazon.latest.usecase.GetLatestMediaUseCase
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val latestModule = module {
    single { provideLatestApi(get((named(AMAZON_NAMED_QUALIFIER)))) }
    factory { provideLatestRepository(get()) }
    factory { provideLatestUseCase(get()) }
    factory { provideLatestMediaViewModel(get(), get(), get()) }
}


private fun provideLatestApi(retrofit: Retrofit): LatestApi {
    return retrofit.create(LatestApi::class.java)
}

private fun provideLatestRepository(latestApi: LatestApi): LatestRepository {
    return LatestRepository(latestApi)
}

private fun provideLatestUseCase(latestRepository: LatestRepository): GetLatestMediaUseCase {
    return GetLatestMediaUseCase(latestRepository)
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