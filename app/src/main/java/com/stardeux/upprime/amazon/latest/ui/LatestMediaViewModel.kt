package com.stardeux.upprime.amazon.latest.ui

import com.stardeux.upprime.amazon.common.model.domain.MediaPage
import com.stardeux.upprime.amazon.common.viewmodel.AmazonMediaViewModel
import com.stardeux.upprime.amazon.latest.usecase.GetLatestMediaUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase

class LatestMediaViewModel(
    private val getLatestMediaUseCase: GetLatestMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
) : AmazonMediaViewModel(getImdbMovieDetailsUseCase, getImdbSeriesDetailsUseCase) {

    override suspend fun getAmazonMedia(): MediaPage {
        return getLatestMediaUseCase.getLatest()
    }
}