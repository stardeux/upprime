package com.stardeux.upprime.media.common.di

import com.stardeux.upprime.media.common.repository.model.mapper.ShortMediaMapper
import com.stardeux.upprime.media.common.ui.model.MediaDetailsMapper
import com.stardeux.upprime.media.common.ui.GetImdbMediaDetailsUseCaseFacade
import com.stardeux.upprime.media.fiche.ui.mapper.MediaFicheUiMapper
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import org.koin.dsl.module

val commonMediaModule = module {
    factory { provideGetImdbMediaDetailsUseCaseFacade(get()) }
    factory { provideMediaDetailsMapper(get()) }
    factory { provideShortMediaMapper() }
}

private fun provideMediaDetailsMapper(posterMapper: PosterMapper): MediaDetailsMapper {
    return MediaDetailsMapper(posterMapper)
}

private fun provideGetImdbMediaDetailsUseCaseFacade(mediaFicheUiMapper: MediaFicheUiMapper): GetImdbMediaDetailsUseCaseFacade {
    return GetImdbMediaDetailsUseCaseFacade(
        mediaFicheUiMapper
    )
}

private fun provideShortMediaMapper(): ShortMediaMapper {
    return ShortMediaMapper()
}