package com.stardeux.upprime.media.expired.ui

import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.media.common.ui.GetMediaItemUiUseCaseFacade
import com.stardeux.upprime.media.common.ui.model.MediaItemUiMapper
import com.stardeux.upprime.media.expired.usecase.GetExpiredMediaUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import com.stardeux.upprime.tmdbinapp.mapper.ImdbMediaRequestMapper

class ExpiredMediaViewModel(
    private val getExpiredMediaUseCase: GetExpiredMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase,
    mediaItemUiMapper: MediaItemUiMapper,
    imdbMediaRequestMapper: ImdbMediaRequestMapper,
    getMediaItemUiUseCaseFacade: GetMediaItemUiUseCaseFacade,
    analyticsWrapper: AnalyticsWrapper
) : AmazonMediaViewModel(
    getImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase,
    mediaItemUiMapper,
    imdbMediaRequestMapper,
    getMediaItemUiUseCaseFacade,
    analyticsWrapper
) {

    override suspend fun getAmazonMedia(page: Int): MediaPage {
        return getExpiredMediaUseCase.getExpired(page)
    }
}