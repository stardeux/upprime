package com.stardeux.upprime.media.common.di

import com.stardeux.upprime.media.common.ui.model.MediaDetailsMapper
import com.stardeux.upprime.media.common.usecase.GetImdbMediaDetailsUseCaseFacade
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import org.koin.dsl.module

val commonMediaModule = module {
    factory { provideGetImdbMediaDetailsUseCase() }
    factory { provideMediaDetailsMapper(get()) }
}

private fun provideGetImdbMediaDetailsUseCase() : GetImdbMediaDetailsUseCaseFacade {
    return GetImdbMediaDetailsUseCaseFacade()
}

private fun provideMediaDetailsMapper(posterMapper: PosterMapper) : MediaDetailsMapper {
    return MediaDetailsMapper(posterMapper)
}