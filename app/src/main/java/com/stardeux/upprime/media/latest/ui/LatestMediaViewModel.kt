package com.stardeux.upprime.media.latest.ui

import com.stardeux.upprime.core.analytics.AnalyticsWrapper
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.media.common.ui.model.MediaItemUiMapper
import com.stardeux.upprime.media.latest.usecase.GetLatestMediaUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase
import com.stardeux.upprime.tmdbinapp.mapper.ImdbMediaRequestMapper

class LatestMediaViewModel(
    private val getLatestMediaUseCase: GetLatestMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase,
    mediaItemUiMapper: MediaItemUiMapper,
    imdbMediaRequestMapper: ImdbMediaRequestMapper,
    analyticsWrapper: AnalyticsWrapper
) : AmazonMediaViewModel(
    getImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase,
    mediaItemUiMapper,
    imdbMediaRequestMapper,
    analyticsWrapper
) {

    override suspend fun getAmazonMedia(page: Int): MediaPage {
        return getLatestMediaUseCase.getLatest(page)
    }
}