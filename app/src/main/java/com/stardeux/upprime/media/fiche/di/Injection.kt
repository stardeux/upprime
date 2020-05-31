package com.stardeux.upprime.media.fiche.di

import android.content.Context
import com.stardeux.upprime.media.common.ui.GetImdbMediaDetailsUseCaseFacade
import com.stardeux.upprime.media.fiche.ui.MediaFicheViewModel
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUiMapper
import com.stardeux.upprime.media.fiche.usecase.MediaIllustrationUseCase
import com.stardeux.upprime.rate.usecase.RateAppUseCase
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoMapper
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.credit.ui.CreditUseCaseFacade
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import org.koin.dsl.module

val ficheModule = module {
    factory { provideMediaIllustrationUseCase(get()) }
    factory { provideMediaVideoMapper() }
    factory { provideMediaFicheUiMapper(get()) }
    factory { provideMediaFicheViewModel(get(), get(), get(), get(), get(), get()) }
}

private fun provideMediaIllustrationUseCase(context: Context): MediaIllustrationUseCase {
    return MediaIllustrationUseCase(context)
}

private fun provideMediaFicheViewModel(
    getImdbMediaDetailsUseCaseFacade: GetImdbMediaDetailsUseCaseFacade,
    videoUseCase: VideoUseCase,
    creditUseCaseFacade: CreditUseCaseFacade,
    mediaVideoMapper: MediaVideoMapper,
    mediaIllustrationUseCase: MediaIllustrationUseCase,
    rateAppUseCase: RateAppUseCase
): MediaFicheViewModel {
    return MediaFicheViewModel(
        getImdbMediaDetailsUseCaseFacade,
        videoUseCase,
        creditUseCaseFacade,
        mediaVideoMapper,
        mediaIllustrationUseCase,
        rateAppUseCase
    )
}

private fun provideMediaVideoMapper(): MediaVideoMapper {
    return MediaVideoMapper()
}

private fun provideMediaFicheUiMapper(posterMapper: PosterMapper): MediaFicheUiMapper {
    return MediaFicheUiMapper(posterMapper)
}