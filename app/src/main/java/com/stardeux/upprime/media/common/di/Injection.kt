package com.stardeux.upprime.media.common.di

import com.stardeux.upprime.media.common.usecase.GetImdbMediaDetailsUseCase
import org.koin.dsl.module

val commonMediaModule = module {
    factory { provideGetImdbMediaDetailsUseCase() }
}

private fun provideGetImdbMediaDetailsUseCase() : GetImdbMediaDetailsUseCase {
    return GetImdbMediaDetailsUseCase()
}