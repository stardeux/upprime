package com.stardeux.upprime.rate.di

import android.content.SharedPreferences
import com.stardeux.upprime.rate.repository.RateAppAnswerMapper
import com.stardeux.upprime.rate.repository.RateAppRepository
import com.stardeux.upprime.rate.usecase.RateAppUseCase
import org.koin.dsl.module

val rateAppModule = module {
    factory { provideRateAppAnswerMapper() }
    factory { provideRateAppRepository(get(), get()) }
    single { provideRateAppUseCase(get()) } //Single to keep a track on favorable actions
}

fun provideRateAppAnswerMapper(): RateAppAnswerMapper {
    return RateAppAnswerMapper()
}

fun provideRateAppRepository(
    sharedPreferences: SharedPreferences, rateAppAnswerMapper: RateAppAnswerMapper
): RateAppRepository {
    return RateAppRepository(sharedPreferences, rateAppAnswerMapper)
}

fun provideRateAppUseCase(rateAppRepository: RateAppRepository): RateAppUseCase {
    return RateAppUseCase(rateAppRepository)
}