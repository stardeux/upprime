package com.stardeux.upprime.media.expired.ui

import com.stardeux.upprime.media.common.usecase.model.MediaPage
import com.stardeux.upprime.media.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.media.expired.usecase.GetExpiredMediaUseCase
import com.stardeux.upprime.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.series.usecase.GetImdbSeriesDetailsUseCase

class ExpiredMediaViewModel(
    private val getExpiredMediaUseCase: GetExpiredMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
) : AmazonMediaViewModel(getImdbMovieDetailsUseCase, getImdbSeriesDetailsUseCase) {

    override suspend fun getAmazonMedia(page: Int): MediaPage {
        return getExpiredMediaUseCase.getExpired(page)
    }
}