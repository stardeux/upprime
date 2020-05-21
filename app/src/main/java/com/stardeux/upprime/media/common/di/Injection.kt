package com.stardeux.upprime.media.common.di

import com.stardeux.upprime.media.common.usecase.GetImdbMediaDetailsUseCaseFacade
import org.koin.dsl.module

val commonMediaModule = module {
    factory { provideGetImdbMediaDetailsUseCase() }
}

private fun provideGetImdbMediaDetailsUseCase() : GetImdbMediaDetailsUseCaseFacade {
    return GetImdbMediaDetailsUseCaseFacade()
}