package com.stardeux.upprime.media.expired.ui

import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.media.common.ui.model.MediaDetailsMapper
import com.stardeux.upprime.media.expired.usecase.GetExpiredMediaUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase

class ExpiredMediaViewModel(
    private val getExpiredMediaUseCase: GetExpiredMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase,
    mediaDetailsMapper: MediaDetailsMapper
) : AmazonMediaViewModel(getImdbMovieDetailsUseCase, getImdbSeriesDetailsUseCase, mediaDetailsMapper) {

    override suspend fun getAmazonMedia(page: Int): MediaPage {
        return getExpiredMediaUseCase.getExpired(page)
    }
}