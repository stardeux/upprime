package com.stardeux.upprime.amazon.latest.ui

import com.stardeux.upprime.amazon.common.usecase.model.MediaPage
import com.stardeux.upprime.amazon.common.ui.AmazonMediaViewModel
import com.stardeux.upprime.amazon.latest.usecase.GetLatestMediaUseCase
import com.stardeux.upprime.tmdb.movie.usecase.GetImdbMovieDetailsUseCase
import com.stardeux.upprime.tmdb.series.usecase.GetImdbSeriesDetailsUseCase

class LatestMediaViewModel(
    private val getLatestMediaUseCase: GetLatestMediaUseCase,
    getImdbMovieDetailsUseCase: GetImdbMovieDetailsUseCase,
    getImdbSeriesDetailsUseCase: GetImdbSeriesDetailsUseCase
) : AmazonMediaViewModel(getImdbMovieDetailsUseCase, getImdbSeriesDetailsUseCase) {

    override suspend fun getAmazonMedia(page: Int): MediaPage {
        return getLatestMediaUseCase.getLatest(page)
    }
}