package com.stardeux.upprime.media.common.di

import com.stardeux.upprime.media.common.ui.model.MediaDetailsMapper
import com.stardeux.upprime.media.common.ui.GetImdbMediaDetailsUseCaseFacade
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUiMapper
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import org.koin.dsl.module

val commonMediaModule = module {
    factory { provideGetImdbMediaDetailsUseCaseFacade(get()) }
    factory { provideMediaDetailsMapper(get()) }
}

private fun provideMediaDetailsMapper(posterMapper: PosterMapper): MediaDetailsMapper {
    return MediaDetailsMapper(posterMapper)
}

private fun provideGetImdbMediaDetailsUseCaseFacade(mediaFicheUiMapper: MediaFicheUiMapper): GetImdbMediaDetailsUseCaseFacade {
    return GetImdbMediaDetailsUseCaseFacade(
        mediaFicheUiMapper
    )
}