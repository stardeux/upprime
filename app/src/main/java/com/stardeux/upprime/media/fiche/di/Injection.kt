package com.stardeux.upprime.media.fiche.di

import android.content.Context
import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.core.usecase.IsIntentResolvableUseCase
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.ui.GetMediaFicheUiUseCaseFacade
import com.stardeux.upprime.media.fiche.ui.MediaFicheFragment
import com.stardeux.upprime.media.fiche.ui.MediaFicheViewModel
import com.stardeux.upprime.media.fiche.ui.mapper.MediaFicheUiMapper
import com.stardeux.upprime.media.fiche.usecase.MediaIllustrationUseCase
import com.stardeux.upprime.rate.usecase.RateAppUseCase
import com.stardeux.upprime.media.fiche.ui.mapper.GetPlayAmazonVideoNativeUriMapper
import com.stardeux.upprime.media.fiche.usecase.GetPlayAmazonVideoIntentUseCase
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.credit.ui.CreditUseCaseFacade
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoMapper
import com.stardeux.upprime.tmdb.video.usecase.VideoUseCase
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val ficheModule = module {
    factory { provideMediaIllustrationUseCase(get()) }
    factory { provideMediaVideoMapper() }
    factory { provideAmazonPlayUriMapper() }
    factory { provideMediaFicheUiMapper(get(), get()) }
    factory { provideAmazonPlayUriUseCase(get(), get()) }
    factory { (fragment: MediaFicheFragment) -> provideShortMedia(fragment) }
    factory { (fragment: MediaFicheFragment) ->
        provideMediaFicheViewModel(
            get { parametersOf(fragment) }, get(), get(), get(), get(), get(), get(), get(), get()
        )
    }
}

private fun provideShortMedia(mediaFicheFragment: MediaFicheFragment): ShortMedia {
    return mediaFicheFragment.getShortMedia()
}

private fun provideMediaIllustrationUseCase(context: Context): MediaIllustrationUseCase {
    return MediaIllustrationUseCase(context)
}

private fun provideMediaFicheViewModel(
    shortMedia: ShortMedia,
    getMediaFicheUiUseCaseFacade: GetMediaFicheUiUseCaseFacade,
    videoUseCase: VideoUseCase,
    creditUseCaseFacade: CreditUseCaseFacade,
    mediaVideoMapper: MediaVideoMapper,
    mediaIllustrationUseCase: MediaIllustrationUseCase,
    rateAppUseCase: RateAppUseCase,
    analyticsWrapper: AnalyticsWrapper,
    isIntentResolvableUseCase: IsIntentResolvableUseCase,
): MediaFicheViewModel {
    return MediaFicheViewModel(
        shortMedia,
        getMediaFicheUiUseCaseFacade,
        videoUseCase,
        creditUseCaseFacade,
        mediaVideoMapper,
        mediaIllustrationUseCase,
        rateAppUseCase,
        analyticsWrapper,
        isIntentResolvableUseCase
    )
}

private fun provideMediaVideoMapper(): MediaVideoMapper {
    return MediaVideoMapper()
}

private fun provideAmazonPlayUriMapper(): GetPlayAmazonVideoNativeUriMapper {
    return GetPlayAmazonVideoNativeUriMapper()
}

private fun provideAmazonPlayUriUseCase(
    isIntentResolvableUseCase: IsIntentResolvableUseCase,
    getPlayAmazonVideoNativeUriMapper: GetPlayAmazonVideoNativeUriMapper
): GetPlayAmazonVideoIntentUseCase {
    return GetPlayAmazonVideoIntentUseCase(isIntentResolvableUseCase, getPlayAmazonVideoNativeUriMapper)
}

private fun provideMediaFicheUiMapper(
    posterMapper: PosterMapper, getPlayAmazonVideoIntentUseCase: GetPlayAmazonVideoIntentUseCase
): MediaFicheUiMapper {
    return MediaFicheUiMapper(posterMapper, getPlayAmazonVideoIntentUseCase)
}