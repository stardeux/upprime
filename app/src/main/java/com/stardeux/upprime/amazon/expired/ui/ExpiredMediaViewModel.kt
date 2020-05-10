package com.stardeux.upprime.amazon.expired.ui

import com.stardeux.upprime.amazon.common.model.domain.MediaPage
import com.stardeux.upprime.amazon.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.amazon.expired.usecase.GetExpiredMediaUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase

class ExpiredMediaViewModel(
    private val getExpiredMediaUseCase: GetExpiredMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
) : AmazonMediaViewModel(getImdbMovieDetailsUseCase, getImdbSeriesDetailsUseCase) {

    override suspend fun getAmazonMedia(): MediaPage {
        return getExpiredMediaUseCase.getExpired()
    }
}