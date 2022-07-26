package com.stardeux.upprime.media.common.di

import com.stardeux.upprime.media.common.repository.model.mapper.ShortMediaMapper
import com.stardeux.upprime.media.common.ui.model.MediaItemUiMapper
import com.stardeux.upprime.media.common.ui.GetMediaFicheUiUseCaseFacade
import com.stardeux.upprime.media.common.ui.GetMediaItemUiUseCaseFacade
import com.stardeux.upprime.media.fiche.ui.mapper.MediaFicheUiMapper
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdbinapp.mapper.ImdbMediaRequestMapper
import org.koin.dsl.module
import java.util.*

val commonMediaModule = module {
    factory { provideGetImdbMediaDetailsUseCaseFacade(get(), get()) }
    factory { provideMediaDetailsMapper(get()) }
    factory { provideShortMediaMapper() }
    factory { provideImdbMediaRequestMapper(get()) }
    factory { provideGetMediaItemUiUseCaseFacade(get(), get()) }
}

private fun provideMediaDetailsMapper(posterMapper: PosterMapper): MediaItemUiMapper {
    return MediaItemUiMapper(posterMapper)
}

private fun provideGetImdbMediaDetailsUseCaseFacade(
    imdbMediaRequestMapper: ImdbMediaRequestMapper, mediaFicheUiMapper: MediaFicheUiMapper
): GetMediaFicheUiUseCaseFacade {
    return GetMediaFicheUiUseCaseFacade(
        imdbMediaRequestMapper, mediaFicheUiMapper
    )
}

private fun provideShortMediaMapper(): ShortMediaMapper {
    return ShortMediaMapper()
}

private fun provideImdbMediaRequestMapper(locale: Locale): ImdbMediaRequestMapper {
    return ImdbMediaRequestMapper(locale)
}

private fun provideGetMediaItemUiUseCaseFacade(
    imdbMediaRequestMapper: ImdbMediaRequestMapper, mediaItemUiMapper: MediaItemUiMapper
): GetMediaItemUiUseCaseFacade {
    return GetMediaItemUiUseCaseFacade(imdbMediaRequestMapper, mediaItemUiMapper)
}