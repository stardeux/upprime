package com.stardeux.upprime.media.common.di

import com.stardeux.upprime.media.common.repository.model.mapper.ShortMediaMapper
import com.stardeux.upprime.media.common.ui.model.MediaDetailsMapper
import com.stardeux.upprime.media.common.ui.GetImdbMediaDetailsUseCaseFacade
import com.stardeux.upprime.media.common.usecase.GetAmazonIdUseCase
import com.stardeux.upprime.media.fiche.ui.mapper.MediaFicheUiMapper
import com.stardeux.upprime.tmdb.common.mapper.TmdbMediaRequestMapper
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdbinapp.mapper.ImdbMediaRequestMapper
import org.koin.dsl.module
import java.util.*

val commonMediaModule = module {
    factory { provideGetImdbMediaDetailsUseCaseFacade(get(), get()) }
    factory { provideMediaDetailsMapper(get()) }
    factory { provideShortMediaMapper(get()) }
    factory { provideGetAmazonIdUseCase() }
    factory { provideImdbMediaRequestMapper(get()) }
}

private fun provideMediaDetailsMapper(posterMapper: PosterMapper): MediaDetailsMapper {
    return MediaDetailsMapper(posterMapper)
}

private fun provideGetImdbMediaDetailsUseCaseFacade(
    imdbMediaRequestMapper: ImdbMediaRequestMapper, mediaFicheUiMapper: MediaFicheUiMapper
): GetImdbMediaDetailsUseCaseFacade {
    return GetImdbMediaDetailsUseCaseFacade(
        imdbMediaRequestMapper, mediaFicheUiMapper
    )
}

private fun provideShortMediaMapper(getAmazonIdUseCase: GetAmazonIdUseCase): ShortMediaMapper {
    return ShortMediaMapper(getAmazonIdUseCase)
}

private fun provideGetAmazonIdUseCase(): GetAmazonIdUseCase {
    return GetAmazonIdUseCase()
}

private fun provideImdbMediaRequestMapper(locale: Locale): ImdbMediaRequestMapper {
    return ImdbMediaRequestMapper(locale)
}