package com.stardeux.upprime.latest.di

import com.stardeux.upprime.latest.repository.LatestRepository
import com.stardeux.upprime.latest.repository.api.LatestApi
import com.stardeux.upprime.latest.ui.LatestMediaViewModel
import com.stardeux.upprime.latest.usecase.GetLatestUseCase
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val latestModule = module {
    single { provideLatestApi(get((named(AMAZON_NAMED_QUALIFIER)))) }
    factory { provideLatestRepository(get()) }
    factory { provideLatestUseCase(get()) }
    factory { provideLatestMediaViewModel(get()) }
}


fun provideLatestApi(retrofit: Retrofit): LatestApi {
    return retrofit.create(LatestApi::class.java)
}

fun provideLatestRepository(latestApi: LatestApi): LatestRepository {
    return LatestRepository(latestApi)
}

fun provideLatestUseCase(latestRepository: LatestRepository): GetLatestUseCase {
    return GetLatestUseCase(latestRepository)
}

fun provideLatestMediaViewModel(getLatestUseCase: GetLatestUseCase): LatestMediaViewModel {
    return LatestMediaViewModel(getLatestUseCase)
}