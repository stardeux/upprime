package com.stardeux.upprime.media.latest.ui

import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.media.common.ui.model.MediaDetailsMapper
import com.stardeux.upprime.media.latest.usecase.GetLatestMediaUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase

class LatestMediaViewModel(
    private val getLatestMediaUseCase: GetLatestMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase,
    mediaDetailsMapper: MediaDetailsMapper
) : AmazonMediaViewModel(getImdbMovieDetailsUseCase, getImdbSeriesDetailsUseCase, mediaDetailsMapper) {

    override suspend fun getAmazonMedia(page: Int): MediaPage {
        return getLatestMediaUseCase.getLatest(page)
    }
}