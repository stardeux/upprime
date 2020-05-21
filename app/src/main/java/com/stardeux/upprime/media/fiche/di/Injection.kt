package com.stardeux.upprime.media.fiche.di

import com.stardeux.upprime.media.common.usecase.GetImdbMediaDetailsUseCase
import com.stardeux.upprime.media.fiche.ui.MediaFicheViewModel
import com.stardeux.upprime.media.fiche.ui.video.MediaVideoMapper
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import org.koin.dsl.module

val ficheModule = module {
    factory { provideMediaVideoMapper() }
    factory { provideMediaFicheViewModel(get(), get(), get()) }
}

private fun provideMediaFicheViewModel(
    getImdbMediaDetailsUseCase: GetImdbMediaDetailsUseCase,
    videoUseCase: VideoUseCase,
    mediaVideoMapper: MediaVideoMapper
): MediaFicheViewModel {
    return MediaFicheViewModel(getImdbMediaDetailsUseCase, videoUseCase, mediaVideoMapper)
}

private fun provideMediaVideoMapper(): MediaVideoMapper {
    return MediaVideoMapper()
}