package com.stardeux.upprime.media.fiche.di

import com.stardeux.upprime.media.common.usecase.GetImdbMediaDetailsUseCaseFacade
import com.stardeux.upprime.media.fiche.ui.MediaFicheViewModel
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUiMapper
import com.stardeux.upprime.media.fiche.ui.video.MediaVideoMapper
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import org.koin.dsl.module

val ficheModule = module {
    factory { provideMediaVideoMapper() }
    factory { provideMediaFicheUiMapper(get()) }
    factory { provideMediaFicheViewModel(get(), get(), get()) }
}

private fun provideMediaFicheViewModel(
    getImdbMediaDetailsUseCaseFacade: GetImdbMediaDetailsUseCaseFacade,
    videoUseCase: VideoUseCase,
    mediaVideoMapper: MediaVideoMapper
): MediaFicheViewModel {
    return MediaFicheViewModel(getImdbMediaDetailsUseCaseFacade, videoUseCase, mediaVideoMapper)
}

private fun provideMediaVideoMapper(): MediaVideoMapper {
    return MediaVideoMapper()
}

private fun provideMediaFicheUiMapper(posterMapper: PosterMapper): MediaFicheUiMapper {
    return MediaFicheUiMapper(posterMapper)
}